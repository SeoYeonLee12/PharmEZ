package com.PharmEZ.PharmEZback.pharmacy.controller;


import static com.PharmEZ.PharmEZback.pharmacy.utility.PharmacyMapper.toListPharmacyListInfoResponse;
import static com.PharmEZ.PharmEZback.pharmacy.utility.PharmacyMapper.toListPharmacyLocationListResponse;
import static com.PharmEZ.PharmEZback.pharmacy.utility.PharmacyMapper.toListPharmacySearchResultResponse;

import com.PharmEZ.PharmEZback.pharmacy.dto.request.MyLocationRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.request.PharmacySearchByIdRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.request.PharmacySearchByNameRequest;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyLocationListResponse;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacySearchResultResponse;
import com.PharmEZ.PharmEZback.pharmacy.exception.NearbyPharmacyNotFoundException;
import com.PharmEZ.PharmEZback.pharmacy.exception.PharmacyNotFoundException;
import com.PharmEZ.PharmEZback.pharmacy.service.PharmacyService;
import com.PharmEZ.PharmEZback.stock.dto.request.PharmacySearchRequest;
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

    @GetMapping("/marker")
    public ResponseEntity<List<PharmacyLocationListResponse>> findPharmacyByLocationForMap(@ModelAttribute MyLocationRequest myLocationRequest){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            List<PharmacyLocationListResponse> response = pharmacyService.findPharmacyByLocationForMap(myLocationRequest);

            return ResponseEntity.status(200)
                    .headers(headers)
                    .body(response);
        }
        catch (NearbyPharmacyNotFoundException ex) {
            List<PharmacyLocationListResponse> errorResponse = toListPharmacyLocationListResponse(PharmacyLocationListResponse.builder()
                    .message("Not NearBy pharmacies")
                    .build());
            return ResponseEntity.status(404)
                    .body(errorResponse);
        }
        catch (Exception ex) {
            List<PharmacyLocationListResponse> errorResponse = toListPharmacyLocationListResponse(PharmacyLocationListResponse.builder()
                    .message("error: " + ex.getMessage())
                    .build());
            return ResponseEntity.status(500)
                    .body(errorResponse);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<List<PharmacySearchResultResponse>> findPharmacyByPharmacyName(
            @ModelAttribute PharmacySearchByNameRequest pharmacySearchByNameRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            Pageable pageable = PageRequest.of(page, size);

            List<PharmacySearchResultResponse> resultResponse= pharmacyService.findPharmacyByPharmacyName(pharmacySearchByNameRequest, pageable);
            return ResponseEntity.status(200)
                    .headers(headers)
                    .body(resultResponse);
        }
        catch (PharmacyNotFoundException ex){
            List<PharmacySearchResultResponse> errorResponse = toListPharmacySearchResultResponse(PharmacySearchResultResponse.builder()
                    .message("medicineName"+pharmacySearchByNameRequest.getName())
                    .build());

            return ResponseEntity.status(404)
                    .body(errorResponse);
        }
        catch (Exception ex){
            List<PharmacySearchResultResponse> errorResponse = toListPharmacySearchResultResponse(PharmacySearchResultResponse.builder()
                    .message("error: "+ex.getMessage())
                    .build());

            return ResponseEntity.status(500)
                    .body(errorResponse);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<PharmacySearchResultResponse> findPharmacyByPharmacyName(@ModelAttribute
                                                                                         PharmacySearchByIdRequest pharmacySearchByIdRequest){

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");

            PharmacySearchResultResponse resultResponse= pharmacyService.findPharmacyByPharmacyId(pharmacySearchByIdRequest);
            return ResponseEntity.status(200)
                    .headers(headers)
                    .body(resultResponse);
        }
        catch (PharmacyNotFoundException ex){
            PharmacySearchResultResponse errorResponse = PharmacySearchResultResponse.builder()
                    .message("medicineId"+pharmacySearchByIdRequest.getId())
                    .build();

            return ResponseEntity.status(404)
                    .body(errorResponse);
        }
        catch (Exception ex){
            PharmacySearchResultResponse errorResponse = PharmacySearchResultResponse.builder()
                    .message("error: "+ex.getMessage())
                    .build();

            return ResponseEntity.status(500)
                    .body(errorResponse);
        }
    }
}
