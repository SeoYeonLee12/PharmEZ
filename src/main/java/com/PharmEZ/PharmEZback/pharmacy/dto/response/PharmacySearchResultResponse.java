package com.PharmEZ.PharmEZback.pharmacy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmacySearchResultResponse {

    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;

    private Double distance;

    private String isOpen;

    private String closeTime;

    private String message;

    public PharmacySearchResultResponse(Long id, String name, Double latitude, Double longitude, Double distance,
                                        String isOpen, String closeTime) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.isOpen = isOpen;
        this.closeTime = closeTime;
        this.message = null;
    }
}
