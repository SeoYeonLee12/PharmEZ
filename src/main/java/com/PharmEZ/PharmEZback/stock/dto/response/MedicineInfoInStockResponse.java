package com.PharmEZ.PharmEZback.stock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MedicineInfoInStockResponse {

    private String medicineName;

    private String image;

    private String message;

    public MedicineInfoInStockResponse(String medicineName, String image) {
        this.medicineName = medicineName;
        this.image = image;
        this.message = null;
    }
}
