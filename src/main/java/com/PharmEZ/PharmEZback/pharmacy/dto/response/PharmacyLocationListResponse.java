package com.PharmEZ.PharmEZback.pharmacy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyLocationListResponse {

    private Long id;

    private Double latitude;

    private Double longitude;

    private String message;

    public PharmacyLocationListResponse(Long id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = null;
    }
}
