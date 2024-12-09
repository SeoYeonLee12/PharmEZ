package com.PharmEZ.PharmEZback.stock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyInfoResponse {
    private String address;

    private String name;

    private String tel;

    // 현재 위치에서 떨어진 약국까지 km
    private Double distance;

    private String message;

    public PharmacyInfoResponse(String address, String name, String tel, Double distance) {
        this.address = address;
        this.name = name;
        this.tel = tel;
        this.distance = distance;
        this.message = null;
    }
}
