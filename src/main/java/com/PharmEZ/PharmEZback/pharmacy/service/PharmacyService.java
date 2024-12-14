package com.PharmEZ.PharmEZback.pharmacy.service;


import com.PharmEZ.PharmEZback.pharmacy.dto.request.MyLocationRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.request.PharmacySearchByIdRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.request.PharmacySearchByNameRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyLocationListResponse;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacySearchResultResponse;
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

    List<PharmacyLocationListResponse> findPharmacyByLocationForMap(MyLocationRequest myLocationRequest);

    List<PharmacySearchResultResponse> findPharmacyByPharmacyName(PharmacySearchByNameRequest pharmacySearchByNameRequest, Pageable pageable);

    PharmacySearchResultResponse findPharmacyByPharmacyId(PharmacySearchByIdRequest pharmacySearchByIdRequest);
}
