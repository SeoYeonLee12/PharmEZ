package com.PharmEZ.PharmEZback.pharmacy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyLocationRequest {

    private Double latitude;

    private Double longitude;

}
