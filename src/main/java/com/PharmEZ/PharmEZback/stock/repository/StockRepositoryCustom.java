package com.PharmEZ.PharmEZback.stock.repository;

import com.PharmEZ.PharmEZback.stock.dto.response.MedicineInfoInStockResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StockRepositoryCustom {

    /**
     * 약국 당 재고가 있는 약을 보여주는 메소드입니다.
     * @param pharmacyId
     * @param pageable
     * @return List<MedicineInfoInStockResponse>
     *
     * @author sylee
     */
    List<MedicineInfoInStockResponse> findMedicineOnPharmacyByStock(Long pharmacyId, Pageable pageable);
}
