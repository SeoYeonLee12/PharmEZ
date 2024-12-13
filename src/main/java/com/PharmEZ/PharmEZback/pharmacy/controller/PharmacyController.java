package com.PharmEZ.PharmEZback.pharmacy.controller;


import static com.PharmEZ.PharmEZback.pharmacy.utility.PharmacyMapper.toListPharmacyListInfoResponse;

import com.PharmEZ.PharmEZback.pharmacy.dto.request.MyLocationRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import com.PharmEZ.PharmEZback.pharmacy.exception.NearbyPharmacyNotFoundException;
import com.PharmEZ.PharmEZback.pharmacy.service.PharmacyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @GetMapping("")
    public ResponseEntity<List<PharmacyListInfoResponse>> findPharmacyByLocation(@ModelAttribute MyLocationRequest myLocationRequest,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            Pageable pageable = PageRequest.of(page, size);
            List<PharmacyListInfoResponse> response = pharmacyService.findPharmacyByLocation(myLocationRequest, pageable);
            return ResponseEntity.status(200)
                    .headers(headers)
                    .body(response);
        }
        catch (NearbyPharmacyNotFoundException ex) {
            List<PharmacyListInfoResponse> errorResponse = toListPharmacyListInfoResponse(PharmacyListInfoResponse.builder()
                    .message("Not NearBy pharmacies")
                    .build());
            return ResponseEntity.status(404)
                    .body(errorResponse);
        }
        catch (Exception ex) {
            List<PharmacyListInfoResponse> errorResponse = toListPharmacyListInfoResponse(PharmacyListInfoResponse.builder()
                    .message("error: " + ex.getMessage())
                    .build());
            return ResponseEntity.status(500)
                    .body(errorResponse);
        }
    }
}
