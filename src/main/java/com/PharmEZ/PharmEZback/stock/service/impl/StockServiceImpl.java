package com.PharmEZ.PharmEZback.stock.service.impl;


import com.PharmEZ.PharmEZback.medicine.entity.Medicine;
import com.PharmEZ.PharmEZback.medicine.repository.MedicineRepository;
import com.PharmEZ.PharmEZback.pharmacy.entity.Pharmacy;
import com.PharmEZ.PharmEZback.pharmacy.repository.PharmacyRepository;
import com.PharmEZ.PharmEZback.stock.dto.request.PharmacySearchRequest;
import com.PharmEZ.PharmEZback.stock.dto.request.StockInfoRequest;
import com.PharmEZ.PharmEZback.stock.dto.request.StockUpdateInfo;
import com.PharmEZ.PharmEZback.stock.dto.response.MedicineInfoInStockResponse;
import com.PharmEZ.PharmEZback.stock.dto.response.PharmacyInfoResponse;
import com.PharmEZ.PharmEZback.stock.entity.Stock;
import com.PharmEZ.PharmEZback.stock.repository.StockRepository;
import com.PharmEZ.PharmEZback.stock.service.StockService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;

    /**
     * findMedicineOnPharmacyByStock
     *
     * @param pharmacyId
     * @param pageable
     * @return List<MedicineInfoInStockResponse>
     *
     * @author sylee
     */
    @Override
    public List<MedicineInfoInStockResponse> findMedicineOnPharmacyByStock(Long pharmacyId, Pageable pageable) {
        return stockRepository.findMedicineOnPharmacyByStock(pharmacyId, pageable);
    }

    /**
     * saveStockByPharmacy
     *
     * @param stockInfoRequest
     * @return String
     *
     * @author sylee
     */
    @Override
    public String saveStockByPharmacy(StockInfoRequest stockInfoRequest) {
        Pharmacy pharmacy = pharmacyRepository.findById(stockInfoRequest.getPharmacyId()).get();
        Medicine medicine= medicineRepository.findById(stockInfoRequest.getMedicineId()).get();
        Stock stock = Stock.builder()
                .pharmacy(pharmacy)
                .medicine(medicine)
                .isOutOfStock(stockInfoRequest.getIsOutOfStock())
                .build();
        stockRepository.saveAndFlush(stock);
        return "success";
    }

    /**
     * updatedStockByPharmacy
     *
     * @param stockUpdateInfo
     * @return String
     *
     * @author sylee
     */
    @Override
    public String updatedStockStatus(StockUpdateInfo stockUpdateInfo) {
        stockRepository.updatedStockStatus(stockUpdateInfo);
        return "updated success";
    }

    /**
     * findMedicineByLocationBasedPharmacy
     *
     * @param pharmacySearchRequest
     * @param pageable
     * @return List<PharmacyInfoResponse>
     *
     * @author sylee
     */
    @Override
    public List<PharmacyInfoResponse> findMedicineByLocationBasedPharmacy(PharmacySearchRequest pharmacySearchRequest, Pageable pageable) {
        return stockRepository.findMedicineByLocationBasedPharmacy(pharmacySearchRequest, pageable);
    }
}
