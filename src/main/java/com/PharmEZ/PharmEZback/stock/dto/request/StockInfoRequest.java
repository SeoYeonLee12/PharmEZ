package com.PharmEZ.PharmEZback.stock.dto.request;

import lombok.Getter;

@Getter
public class StockInfoRequest {

    private Long pharmacyId;

    private Long medicineId;

    private Boolean isOutOfStock;

    public StockInfoRequest(Long pharmacyId, Long medicineId) {
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
        this.isOutOfStock = false;
    }
}
