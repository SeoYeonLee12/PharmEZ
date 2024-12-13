package com.PharmEZ.PharmEZback.pharmacy.repository;

import com.PharmEZ.PharmEZback.pharmacy.dto.request.MyLocationRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PharmacyRepositoryCustom {

    List<PharmacyListInfoResponse> findPharmacyByLocation(MyLocationRequest myLocationRequest, Pageable pageable);
}
