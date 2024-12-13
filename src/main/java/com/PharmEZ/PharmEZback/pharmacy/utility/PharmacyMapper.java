package com.PharmEZ.PharmEZback.pharmacy.utility;

import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PharmacyMapper {

    public static List<PharmacyListInfoResponse> toListPharmacyListInfoResponse(PharmacyListInfoResponse... responses) {
        return Arrays.stream(responses)
                .collect(Collectors.toList());
    }
}
