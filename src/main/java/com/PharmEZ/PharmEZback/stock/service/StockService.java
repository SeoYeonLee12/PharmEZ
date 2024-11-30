package com.PharmEZ.PharmEZback.stock.service;

import com.PharmEZ.PharmEZback.stock.dto.response.MedicineInfoInStockResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface StockService {

    /**
     * findMedicineOnPharmacyByStock
     * @param pharmacyId
     * @param pageable
     * @return List<MedicineInfoInStockResponse>
     * @author sylee
     */
    List<MedicineInfoInStockResponse> findMedicineOnPharmacyByStock(Long pharmacyId, Pageable pageable);
}
