package com.PharmEZ.PharmEZback.pharmacy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacySearchByIdRequest {

    private Long id;

    private Double latitude;

    private Double longitude;

    private String message;

    public PharmacySearchByIdRequest(Long id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = null;
    }
}
