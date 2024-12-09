package com.PharmEZ.PharmEZback.stock.repository;

import com.PharmEZ.PharmEZback.stock.dto.request.PharmacySearchRequest;
import com.PharmEZ.PharmEZback.stock.dto.request.StockUpdateInfo;
import com.PharmEZ.PharmEZback.stock.dto.response.MedicineInfoInStockResponse;
import com.PharmEZ.PharmEZback.stock.dto.response.PharmacyInfoResponse;
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

    /**
     * 품절 또는 입고 되었을 경우 제고 상태를 업데이트하는 메소드입니다.
     *
     * @param stockUpdateInfo
     * @return String
     *
     * @author sylee
     */
    String updatedStockStatus(StockUpdateInfo stockUpdateInfo);

    /**
     * 약 명 검색 시 재고를 보유하고 있는 근처 약국 목록을 조회하는 메소드입니다.
     *
     * @param pharmacySearchRequest
     * @return PharmacyInfoResponse
     *
     * @author sylee
     */
    List<PharmacyInfoResponse> findMedicineByLocationBasedPharmacy(PharmacySearchRequest pharmacySearchRequest, Pageable pageable);
}
