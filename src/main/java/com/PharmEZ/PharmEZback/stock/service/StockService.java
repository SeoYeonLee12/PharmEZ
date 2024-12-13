package com.PharmEZ.PharmEZback.stock.service;

import com.PharmEZ.PharmEZback.stock.dto.request.PharmacySearchRequest;
import com.PharmEZ.PharmEZback.stock.dto.request.StockInfoRequest;
import com.PharmEZ.PharmEZback.stock.dto.request.StockUpdateInfo;
import com.PharmEZ.PharmEZback.stock.dto.response.MedicineInfoInStockResponse;
import com.PharmEZ.PharmEZback.stock.dto.response.PharmacyInfoResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface StockService {

    /**
     * findMedicineOnPharmacyByStock
     *
     * @param pharmacyId
     * @param pageable
     * @return List<MedicineInfoInStockResponse>
     *
     * @author sylee
     */
    List<MedicineInfoInStockResponse> findMedicineOnPharmacyByStock(Long pharmacyId, Pageable pageable);

    /**
     * saveStockByPharmacy
     *
     * @param stockInfoRequest
     * @return String
     *
     * @author sylee
     */
    String saveStockByPharmacy(StockInfoRequest stockInfoRequest);

    /**
     * updatedStockByPharmacy
     *
     * @param stockUpdateInfo
     * @return updatedStockStatus
     *
     * @author sylee
     */
    String updatedStockStatus(StockUpdateInfo stockUpdateInfo);

    /**
     * findMedicineByLocationBasedPharmacy
     *
     * @param pharmacySearchRequest
     * @param pageable
     * @return List<PharmacyInfoResponse>
     *
     * @author sylee
     */
    List<PharmacyInfoResponse> findMedicineByLocationBasedPharmacy(PharmacySearchRequest pharmacySearchRequest, Pageable pageable);
}
