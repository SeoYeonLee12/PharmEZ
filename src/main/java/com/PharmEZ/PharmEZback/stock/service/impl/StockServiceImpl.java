package com.PharmEZ.PharmEZback.stock.service.impl;

import com.PharmEZ.PharmEZback.stock.dto.response.MedicineInfoInStockResponse;
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
    final StockRepository stockRepository;

    /**
     * findMedicineOnPharmacyByStock
     *
     * @param pharmacyId
     * @param pageable
     *
     * @return List<MedicineInfoInStockResponse>
     *
     * @author sylee
     */
    @Override
    public List<MedicineInfoInStockResponse> findMedicineOnPharmacyByStock(Long pharmacyId, Pageable pageable) {
        return stockRepository.findMedicineOnPharmacyByStock(pharmacyId, pageable);
    }
}
