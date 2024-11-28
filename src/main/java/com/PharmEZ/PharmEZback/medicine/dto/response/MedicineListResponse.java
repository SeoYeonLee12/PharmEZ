package com.PharmEZ.PharmEZback.medicine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MedicineListResponse {

    private String pharmaceuticalCompany;

    private String medicineName;

    private String image;

    private String message;

}
