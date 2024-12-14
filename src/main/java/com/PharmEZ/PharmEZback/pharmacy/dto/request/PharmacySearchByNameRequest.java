package com.PharmEZ.PharmEZback.pharmacy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PharmacySearchByNameRequest {
    private String name;

    private Double latitude;

    private Double longitude;
}
