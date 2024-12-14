package com.PharmEZ.PharmEZback.pharmacy.repository.impl;


import static com.PharmEZ.PharmEZback.pharmacy.entity.QPharmacy.pharmacy;

import com.PharmEZ.PharmEZback.pharmacy.dto.request.MyLocationRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.request.PharmacySearchByNameRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyLocationListResponse;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacySearchResultResponse;
import com.PharmEZ.PharmEZback.pharmacy.entity.Pharmacy;
import com.PharmEZ.PharmEZback.pharmacy.entity.QPharmacy;
import com.PharmEZ.PharmEZback.pharmacy.repository.PharmacyRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
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

    /**
     * 내 근처 약국리스트를 보여주는 메소드입니다.
     * @param myLocationRequest
     * @param pageable
     * @return
     */
    @Override
    public List<PharmacyListInfoResponse> findPharmacyByLocation(MyLocationRequest myLocationRequest, Pageable pageable) {
        QPharmacy pharmacy = QPharmacy.pharmacy;

        NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_Distance_Sphere(POINT({0}, {1}), POINT({2}, {3}))",
                pharmacy.longitude, pharmacy.latitude,
                myLocationRequest.getLongitude(), myLocationRequest.getLatitude());

        StringExpression currentTime = Expressions.stringTemplate("DATE_FORMAT(NOW(), '%H:%i')");

        NumberExpression<Integer> dayOfWeek = Expressions.numberTemplate(Integer.class,  "DAYOFWEEK(NOW())");

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

    /**
     * 내 위치 주변 약국을 마커로 표시하는 메소드입니다.
     * @param myLocationRequest
     * @return List<PharmacyLocationListResponse>
     *
     * @author sylee
     */
    @Override
    public List<PharmacyLocationListResponse> findPharmacyByLocationForMap(MyLocationRequest myLocationRequest) {
        QPharmacy pharmacy = QPharmacy.pharmacy;

        return from(pharmacy)
                .where(getDistance(myLocationRequest.getLatitude(), myLocationRequest.getLongitude()).loe(5000.0))
                .select(Projections.constructor(PharmacyLocationListResponse.class,
                        pharmacy.id,
                        pharmacy.latitude,
                        pharmacy.longitude
                        ))
                .fetch();
    }

    /**
     * 약국 명으로 약국을 검색하는 메소드입니다.
     * @param pharmacySearchByNameRequest
     * @return List<PharmacySearchResultResponse>
     *
     * @author sylee
     */
    @Override
    public List<PharmacySearchResultResponse> findPharmacyByPharmacyName(PharmacySearchByNameRequest pharmacySearchByNameRequest, Pageable pageable) {
        QPharmacy pharmacy = QPharmacy.pharmacy;
       NumberExpression distance = getDistance(pharmacySearchByNameRequest.getLatitude(), pharmacySearchByNameRequest.getLongitude());
       log.error("distance: " + distance);
        return from(pharmacy)
                .where(pharmacy.name.contains(pharmacySearchByNameRequest.getName()))
                .select(Projections.constructor(PharmacySearchResultResponse.class,
                        pharmacy.id,
                        pharmacy.name,
                        pharmacy.latitude,
                        pharmacy.longitude,
                        distance,
                        isOpen(startTime(), closeTime()),
                        closeTime()
                        ))
                .orderBy(distance.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    /**
     * 사용자와 약국의 위치 사이 거리를 구하는 메소드입니다.
     *
     * @param userLatitude
     * @param userLongitude
     * @return NumberExpression
     * @author sylee
     */
    public NumberExpression getDistance(Double userLatitude, Double userLongitude) {
        QPharmacy pharmacy = QPharmacy.pharmacy;
        return Expressions.numberTemplate(Double.class,
                "ST_Distance_Sphere(POINT({0}, {1}), POINT({2}, {3}))",
                pharmacy.longitude, pharmacy.latitude,
                userLongitude, userLatitude);
    }

    /**
     * 현재 시간을 구하는 메소드입니다.
     * @return StringExpression
     *
     * @author sylee
     */
    public StringExpression currentTime() {
        return Expressions.stringTemplate("NOW()");
    }

    /**
     * 현재 요일을 구하는 메소드입니다.
     *
     * @param currentTime
     * @return NumberExpression
     *
     * @author sylee
     */
    public NumberExpression weekOfDay(StringExpression currentTime){
        return Expressions.numberTemplate(Integer.class,  "DAYOFWEEK({0})", currentTime);
    }

    /**
     * 영업시간인지 확인하는 메소드입니다.
     *
     * @param openTime
     * @param closeTime
     * @return StringExpression
     *
     * @author sylee
     */
    public StringExpression isOpen(StringExpression openTime, StringExpression closeTime){
        return new CaseBuilder().when(currentTime().between(openTime, closeTime)).then("영업중")
                .otherwise("영업 종료");
    }

    /**
     * 시작시간을 비교하는 함수입니다.
     *
     * @return StringExpression
     * @author sylee
     */
    public StringExpression startTime(){
        QPharmacy pharmacy = QPharmacy.pharmacy;
        return new CaseBuilder()
                .when(weekOfDay(currentTime()).eq(1)).then(pharmacy.sunStart)
                .when(weekOfDay(currentTime()).eq(2)).then(pharmacy.monStart)
                .when(weekOfDay(currentTime()).eq(3)).then(pharmacy.tueStart)
                .when(weekOfDay(currentTime()).eq(4)).then(pharmacy.wedStart)
                .when(weekOfDay(currentTime()).eq(5)).then(pharmacy.thuStart)
                .when(weekOfDay(currentTime()).eq(6)).then(pharmacy.friStart)
                .when(weekOfDay(currentTime()).eq(7)).then(pharmacy.satStart)
                .otherwise("");
    }

    /**
     * 종료시간을 비교하는 함수입니다.
     *
     * @return StringExpression
     * @author sylee
     */
    public StringExpression closeTime(){
        QPharmacy pharmacy = QPharmacy.pharmacy;
        return new CaseBuilder()
                .when(weekOfDay(currentTime()).eq(1)).then(pharmacy.sunClose)
                .when(weekOfDay(currentTime()).eq(2)).then(pharmacy.monClose)
                .when(weekOfDay(currentTime()).eq(3)).then(pharmacy.tueClose)
                .when(weekOfDay(currentTime()).eq(4)).then(pharmacy.wedClose)
                .when(weekOfDay(currentTime()).eq(5)).then(pharmacy.thuClose)
                .when(weekOfDay(currentTime()).eq(6)).then(pharmacy.friClose)
                .when(weekOfDay(currentTime()).eq(7)).then(pharmacy.satClose)
                .otherwise("");
    }
}
