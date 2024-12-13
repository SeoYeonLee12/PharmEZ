package com.PharmEZ.PharmEZback.pharmacy.service;


import com.PharmEZ.PharmEZback.pharmacy.dto.request.MyLocationRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PharmacyService {

    /**
     * findPharmacyByLocation
     *
     * @param myLocationRequest
     * @param pageable
     * @return List<PharmacyListInfoResponse>
     *
     * @author sylee
     */
    List<PharmacyListInfoResponse> findPharmacyByLocation(MyLocationRequest myLocationRequest, Pageable pageable);

}
