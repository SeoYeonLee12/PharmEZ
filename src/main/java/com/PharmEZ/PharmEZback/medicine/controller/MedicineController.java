package com.PharmEZ.PharmEZback.medicine.controller;

import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineInfoResponse;
import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineListResponse;
import com.PharmEZ.PharmEZback.medicine.exception.MedicineNotFoundException;
import com.PharmEZ.PharmEZback.medicine.service.MedicineService;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping("/{id}")
    public ResponseEntity<MedicineInfoResponse> getMedicineById(@PathVariable Long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            MedicineInfoResponse response = medicineService.findMedicineById(id);
            return ResponseEntity.status(200)
                    .headers(headers)
                    .body(response);
        }
        catch (MedicineNotFoundException ex) {
//            String result = "Status : " + ResponseEntity.status(404) +"/nid :" +id;
            MedicineInfoResponse errorResponse = MedicineInfoResponse.builder()
                    .message("id: " + id)
                    .build();
            return ResponseEntity.status(404)
                    .body(errorResponse);
        }
        catch (Exception ex) {
            MedicineInfoResponse errorResponse = MedicineInfoResponse.builder()
                    .message("error: " + ex.getMessage())
                    .build();
            return ResponseEntity.status(500)
                    .body(errorResponse);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<MedicineListResponse>> getAllMedicines(@RequestParam String medicineName) {
        String url = "/medicines?medicineName=" + medicineName;
        System.out.println("request url : "+ url);
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            List<MedicineListResponse> response = medicineService.findByMedicineNameContaining(medicineName);
            return ResponseEntity.status(200)
                    .headers(headers)
                    .body(response);
        }
        catch (MedicineNotFoundException ex) {
            MedicineListResponse errorResponse = MedicineListResponse.builder()
                    .message("name" + medicineName)
                    .build();
            return ResponseEntity.status(404)
                    .body(Collections.singletonList(errorResponse));
        }
        catch (Exception ex) {
            MedicineListResponse errorResponse = MedicineListResponse.builder()
                    .message("error: " + ex.getMessage())
                    .build();
            return ResponseEntity.status(500)
                    .body(Collections.singletonList(errorResponse));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicineListResponse>> getAllMedicines() {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            List<MedicineListResponse> response = medicineService.findAllMedicine();
            return ResponseEntity.status(200)
                    .headers(headers)
                    .body(response);
        }
        catch(MedicineNotFoundException ex) {
            MedicineListResponse errorResponse = MedicineListResponse.builder()
                    .message("Any Data not exists for your program.")
                    .build();
            return ResponseEntity.status(404)
                    .body(Collections.singletonList(errorResponse));
        }
        catch (Exception ex) {
            MedicineListResponse errorResponse = MedicineListResponse.builder()
                    .message("error: " + ex.getMessage())
                    .build();
            return ResponseEntity.status(500)
                    .body(Collections.singletonList(errorResponse));
        }
    }
}
