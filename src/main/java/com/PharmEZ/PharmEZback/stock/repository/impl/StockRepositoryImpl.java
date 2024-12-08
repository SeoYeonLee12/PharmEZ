package com.PharmEZ.PharmEZback.stock.repository.impl;

import com.PharmEZ.PharmEZback.medicine.entity.QMedicine;
import com.PharmEZ.PharmEZback.pharmacy.entity.QPharmacy;
import com.PharmEZ.PharmEZback.stock.dto.request.StockUpdateInfo;
import com.PharmEZ.PharmEZback.stock.dto.response.MedicineInfoInStockResponse;
import com.PharmEZ.PharmEZback.stock.entity.QStock;
import com.PharmEZ.PharmEZback.stock.entity.Stock;
import com.PharmEZ.PharmEZback.stock.repository.StockRepositoryCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class StockRepositoryImpl extends QuerydslRepositorySupport implements StockRepositoryCustom {
    public StockRepositoryImpl() {
        super(Stock.class);
    }

    /**
     * 약국 당 재고가 있는 약의 정보를 보여주는 메소드입니다.
     *
     * @param pharmacyId
     * @param pageable
     * @return List<MedicineInfoInStockResponse>
     * @author sylee
     */
    @Override
    public List<MedicineInfoInStockResponse> findMedicineOnPharmacyByStock(Long pharmacyId, Pageable pageable) {
        QStock stock = QStock.stock;
        QMedicine medicine = QMedicine.medicine;
        QPharmacy pharmacy = QPharmacy.pharmacy;

        List<MedicineInfoInStockResponse> result = from(stock)
                .innerJoin(pharmacy).on(stock.pharmacy.eq(pharmacy))
                .innerJoin(medicine).on(stock.medicine.eq(medicine))
                .where(stock.isOutOfStock.eq(false).and(pharmacy.id.eq(pharmacyId)))
                .select(Projections.constructor(MedicineInfoInStockResponse.class,
                        medicine.medicineName,
                        medicine.image))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }

    /**
     * 품절되었을 경우 제고 상태를 업데이트하는 메소드입니다.
     *
     * @param stockUpdateInfo
     * @return String
     *
     * @author sylee
     */
    @Override
    public String updatedStockStatus(StockUpdateInfo stockUpdateInfo) {
        QStock stock = QStock.stock;

        update(stock)
                .set(stock.isOutOfStock, stockUpdateInfo.getIsOutOfStock())
                .where(stock.id.eq(stockUpdateInfo.getStockId()))
                .execute();
        return "Successfully updated stock";
    }
}
