package com.PharmEZ.PharmEZback.pharmacy.repository.impl;


import com.PharmEZ.PharmEZback.pharmacy.dto.request.MyLocationRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import com.PharmEZ.PharmEZback.pharmacy.entity.Pharmacy;
import com.PharmEZ.PharmEZback.pharmacy.entity.QPharmacy;
import com.PharmEZ.PharmEZback.pharmacy.repository.PharmacyRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class PharmacyRepositoryImpl extends QuerydslRepositorySupport implements PharmacyRepositoryCustom {

    public PharmacyRepositoryImpl() {
        super(Pharmacy.class);
    }

    @Override
    public List<PharmacyListInfoResponse> findPharmacyByLocation(MyLocationRequest myLocationRequest, Pageable pageable) {
        QPharmacy pharmacy = QPharmacy.pharmacy;

        NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_Distance_Sphere(POINT({0}, {1}), POINT({2}, {3}))",
                pharmacy.longitude, pharmacy.latitude,
                myLocationRequest.getLongitude(), myLocationRequest.getLatitude());

        StringExpression currentTime = Expressions.stringTemplate("NOW()");

        NumberExpression<Integer> dayOfWeek = Expressions.numberTemplate(Integer.class, "DAYOFWEEK({0})", currentTime);

        StringExpression startTime = new CaseBuilder()
                .when(dayOfWeek.eq(1)).then(pharmacy.sunStart)
                .when(dayOfWeek.eq(2)).then(pharmacy.monStart)
                .when(dayOfWeek.eq(3)).then(pharmacy.tueStart)
                .when(dayOfWeek.eq(4)).then(pharmacy.wedStart)
                .when(dayOfWeek.eq(5)).then(pharmacy.thuStart)
                .when(dayOfWeek.eq(6)).then(pharmacy.friStart)
                .when(dayOfWeek.eq(7)).then(pharmacy.satStart)
                .otherwise(pharmacy.publicHolidayStart);

        StringExpression closeTime = new CaseBuilder()
                .when(dayOfWeek.eq(1)).then(pharmacy.sunClose)
                .when(dayOfWeek.eq(2)).then(pharmacy.monClose)
                .when(dayOfWeek.eq(3)).then(pharmacy.tueClose)
                .when(dayOfWeek.eq(4)).then(pharmacy.wedClose)
                .when(dayOfWeek.eq(5)).then(pharmacy.thuClose)
                .when(dayOfWeek.eq(6)).then(pharmacy.friClose)
                .when(dayOfWeek.eq(7)).then(pharmacy.satClose)
                .otherwise(pharmacy.publicHolidayClose);

        StringExpression weekday = new CaseBuilder()
                .when(dayOfWeek.eq(1)).then("일")
                .when(dayOfWeek.eq(2)).then("월")
                .when(dayOfWeek.eq(3)).then("화")
                .when(dayOfWeek.eq(4)).then("수")
                .when(dayOfWeek.eq(5)).then("목")
                .when(dayOfWeek.eq(6)).then("금")
                .when(dayOfWeek.eq(7)).then("토")
                .otherwise("");

        log.debug(startTime+"\n요일"+dayOfWeek+"\n현재시각 "+currentTime+" "+closeTime);

        return from(pharmacy)
                .where(distance.loe(5000.0))
                .select(Projections.constructor(PharmacyListInfoResponse.class,
                        pharmacy.address,
                        pharmacy.name,
                        distance,
                        new CaseBuilder()
                                .when(currentTime.between(startTime, closeTime)).then("영업중")
                                .otherwise("영업종료")
                                .as("isOpen"),
                        weekday.as("weekOfDay"),
                        startTime.as("openTime"),
                        closeTime.as("closeTime")
                ))
                .orderBy(distance.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }
}
