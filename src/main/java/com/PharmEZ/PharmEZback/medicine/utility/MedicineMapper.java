package com.PharmEZ.PharmEZback.medicine.utility;

import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineInfoResponse;
import com.PharmEZ.PharmEZback.medicine.dto.response.MedicineListResponse;
import com.PharmEZ.PharmEZback.medicine.entity.Medicine;
import java.util.List;
import java.util.stream.Collectors;

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
    };

    public static MedicineListResponse toMedicinListResponse(Medicine medicines) {
        return MedicineListResponse.builder()
                .pharmaceuticalCompany(medicines.getPharmaceuticalCompany())
                .medicineName(medicines.getMedicineName())
                .image(medicines.getImage())
                .build();
    };

    public static List<MedicineListResponse> toMedicineListResponses(List<Medicine> medicines) {
        return medicines.stream()
                .map(MedicineMapper::toMedicinListResponse)
                .collect(Collectors.toList());
    }
}
