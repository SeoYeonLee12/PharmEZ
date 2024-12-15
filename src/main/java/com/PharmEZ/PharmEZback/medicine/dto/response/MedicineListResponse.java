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

    private Long id;

    private String pharmaceuticalCompany;

    private String medicineName;

    private String image;

    private String message;

    public MedicineListResponse(Long id, String pharmaceuticalCompany, String medicineName, String image) {
        this.id = id;
        this.pharmaceuticalCompany = pharmaceuticalCompany;
        this.medicineName = medicineName;
        this.image = image;
        this.message=null;
    }
}
