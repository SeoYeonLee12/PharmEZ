package com.PharmEZ.PharmEZback.medicine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineInfoResponse {

    private String pharmaceuticalCompany;

    private String medicineName;

    private String efficacy;

    private String medicineUse;

    private String precautionWarn;

    private String usingPrecaution;

    private String medicineInteractions;

    private String medicineSideEffect;

    private String storage;

    private String image;

    // 오류 메세지를 반환
    private String message;

}
