package com.PharmEZ.PharmEZback.stock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacySearchRequest {

    private Double latitude;

    private Double longitude;

    private String medicineName;
}
