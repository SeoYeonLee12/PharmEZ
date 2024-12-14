package com.PharmEZ.PharmEZback.stock.controller;

import static com.PharmEZ.PharmEZback.stock.utility.StockMapper.toListMedicineInfoInStockResponse;
import static com.PharmEZ.PharmEZback.stock.utility.StockMapper.toListPharmacyInfoResponse;

import com.PharmEZ.PharmEZback.medicine.exception.MedicineNotFoundException;
import com.PharmEZ.PharmEZback.stock.dto.request.PharmacySearchRequest;
import com.PharmEZ.PharmEZback.stock.dto.request.StockInfoRequest;
import com.PharmEZ.PharmEZback.stock.dto.request.StockUpdateInfo;
import com.PharmEZ.PharmEZback.stock.dto.response.MedicineInfoInStockResponse;
import com.PharmEZ.PharmEZback.stock.dto.response.PharmacyInfoResponse;
import com.PharmEZ.PharmEZback.stock.exception.PossessedPharmacyNotFoundException;
import com.PharmEZ.PharmEZback.stock.exception.StockNotFoundExeption;
import com.PharmEZ.PharmEZback.stock.service.StockService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * StockController
 *
 * @author sylee
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping("/pharmacies/{pharmacyId}")
    public ResponseEntity<List<MedicineInfoInStockResponse>> findMedicineOnPharmacyByStock(@PathVariable Long pharmacyId,
                                                                                          @RequestParam(defaultValue = "0") int page,
                                                                                          @RequestParam(defaultValue = "10") int size) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            Pageable pageable = PageRequest.of(page, size);
            List<MedicineInfoInStockResponse> response = stockService.findMedicineOnPharmacyByStock(pharmacyId, pageable);
            return ResponseEntity.status(200)
                    .headers(headers)
                    .body(response);
        } catch (MedicineNotFoundException ex) {
            List<MedicineInfoInStockResponse> errorResponse =
                    toListMedicineInfoInStockResponse(MedicineInfoInStockResponse.builder()
                            .message("약국 id : " + pharmacyId)
                            .build());
            return ResponseEntity.status(404)
                    .body(errorResponse);
        } catch (Exception ex) {
            List<MedicineInfoInStockResponse> errorResponse =
                    toListMedicineInfoInStockResponse(MedicineInfoInStockResponse.builder()
                            .message("error: " + ex.getMessage())
                            .build());
            return ResponseEntity.status(500)
                    .body(errorResponse);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> saveStockByPharmacy(@RequestBody StockInfoRequest stockInfoRequest){

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");

            stockService.saveStockByPharmacy(stockInfoRequest);
            return ResponseEntity.status(201)
                    .headers(headers)
                    .body( "\'status\' : \'created\'");

        }
        catch(MedicineNotFoundException ex){
            return ResponseEntity.status(404)
                    .body("\'status\' : \'medicineNotFound\'");
        }
        catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(500)
                    .body("\'status\' : \'"+ex.getMessage()+"\'");
        }
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<String> updatedStockStatus(@PathVariable Long stockId, @RequestBody StockUpdateInfo stockUpdateInfo){
        try{
            log.info(stockUpdateInfo.getIsOutOfStock().toString());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            stockService.updatedStockStatus(stockUpdateInfo);

            return ResponseEntity.status(200)
                    .headers(headers)
                    .body("\'status\' : \'updated\'");

        }
        catch(StockNotFoundExeption ex){
            return ResponseEntity.status(404)
                    .body("\'status\' : \'Stock not found\'");
        }
        catch(Exception ex){
            return ResponseEntity.status(500)
                    .body("\'status\' : \'"+ex.getMessage()+"\'");
        }

    }

    @GetMapping("/search/{medicineName}")
    public ResponseEntity<List<PharmacyInfoResponse>> findMedicineByLocationBasedPharmacy(@PathVariable String medicineName, @RequestParam(required = false) Double latitude, @RequestParam(required = false) Double longitude, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            Pageable pageable = PageRequest.of(page, size);
            PharmacySearchRequest request = PharmacySearchRequest.builder()
                    .medicineName(medicineName)
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();
            List<PharmacyInfoResponse> response = stockService.findMedicineByLocationBasedPharmacy(request, pageable);
            return ResponseEntity.status(200)
                    .headers(headers)
                    .body(response);
        } catch (PossessedPharmacyNotFoundException ex) {
            List<PharmacyInfoResponse> errorResponse =
                    toListPharmacyInfoResponse(PharmacyInfoResponse.builder()
                            .message("Not exist Pharmacy" + ex)
                            .build());
            return ResponseEntity.status(404)
                    .body(errorResponse);
        } catch (Exception ex) {
            List<PharmacyInfoResponse> errorResponse =
                    toListPharmacyInfoResponse(PharmacyInfoResponse.builder()
                            .message("error: " + ex.getMessage())
                            .build());
            return ResponseEntity.status(500)
                    .body(errorResponse);


        }
    }
}
