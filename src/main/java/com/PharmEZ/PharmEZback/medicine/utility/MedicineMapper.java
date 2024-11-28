package com.PharmEZ.PharmEZback.medicine.utility;

import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineInfoResponse;
import com.PharmEZ.PharmEZback.medicine.entity.Medicine;

public class MedicineMapper {

    public static MedicineInfoResponse toMedicineInfoResponse(Medicine medicine) {

        return MedicineInfoResponse.builder()
                .pharmaceuticalCompany(medicine.getPharmaceuticalCompany())
                .medicineName(medicine.getMedicineName())
                .efficacy(medicine.getEfficacy())
                .medicineUse(medicine.getMedicineUse())
                .precautionWarn(medicine.getPrecautionWarn())
                .usingPrecaution(medicine.getUsingPrecaution())
                .medicineInteractions(medicine.getMedicineInteractions())
                .medicineSideEffect(medicine.getMedicineSideEffect())
                .storage(medicine.getStorage())
                .image(medicine.getImage())
                .build();
    }
}
