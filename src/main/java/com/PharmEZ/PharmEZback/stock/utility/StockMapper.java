package com.PharmEZ.PharmEZback.stock.utility;

import com.PharmEZ.PharmEZback.medicine.utility.MedicineMapper;
import com.PharmEZ.PharmEZback.stock.dto.response.MedicineInfoInStockResponse;
import com.PharmEZ.PharmEZback.stock.dto.response.PharmacyInfoResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StockMapper {

    public static List<MedicineInfoInStockResponse> toListMedicineInfoInStockResponse(MedicineInfoInStockResponse... responses) {
        return Arrays.stream(responses)
                .collect(Collectors.toList());
    }

    public static List<PharmacyInfoResponse> toListPharmacyInfoResponse(PharmacyInfoResponse... response) {
        return Arrays.asList(response);
    }
}
