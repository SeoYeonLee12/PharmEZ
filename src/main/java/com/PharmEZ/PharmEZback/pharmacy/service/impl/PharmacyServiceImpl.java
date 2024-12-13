package com.PharmEZ.PharmEZback.pharmacy.service.impl;

import com.PharmEZ.PharmEZback.pharmacy.dto.request.MyLocationRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import com.PharmEZ.PharmEZback.pharmacy.repository.PharmacyRepository;
import com.PharmEZ.PharmEZback.pharmacy.service.PharmacyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PharmacyServiceImpl
 *
 * @author sylee
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    /**
     * findPharmacyByLocation
     *
     * @param myLocationRequest
     * @param pageable
     * @return List<PharmacyListInfoResponse>
     *
     * @author sylee
     */
    @Override
    public List<PharmacyListInfoResponse> findPharmacyByLocation(MyLocationRequest myLocationRequest,
                                                                 Pageable pageable) {
        return pharmacyRepository.findPharmacyByLocation(myLocationRequest, pageable);
    }
}
