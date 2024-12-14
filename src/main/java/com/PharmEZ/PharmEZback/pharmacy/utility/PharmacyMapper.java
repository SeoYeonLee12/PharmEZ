package com.PharmEZ.PharmEZback.pharmacy.utility;

import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyListInfoResponse;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacyLocationListResponse;
import com.PharmEZ.PharmEZback.pharmacy.dto.response.PharmacySearchResultResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PharmacyMapper {

    public static List<PharmacyListInfoResponse> toListPharmacyListInfoResponse(PharmacyListInfoResponse... responses) {
        return Arrays.stream(responses)
                .collect(Collectors.toList());
    }

    public static List<PharmacyLocationListResponse> toListPharmacyLocationListResponse(PharmacyLocationListResponse... responses) {
        return Arrays.stream(responses)
                .collect(Collectors.toList());
    }

    public static List<PharmacySearchResultResponse> toListPharmacySearchResultResponse(PharmacySearchResultResponse... responses) {
        return Arrays.stream(responses)
                .collect(Collectors.toList());
    }
}
