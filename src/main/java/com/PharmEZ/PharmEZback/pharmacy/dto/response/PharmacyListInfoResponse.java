package com.PharmEZ.PharmEZback.pharmacy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyListInfoResponse {
    private String address;

    private String name;

    private Double distance;

    private String isOpen; // 현재 시간이 받아온 영업 시간 내에 있으면 true 아니면 false

    private String weekOfDay;

    private String openTime; // 현재 시스템 시간에 맞는 요일로..

    private String closeTime;

    private String message;

    public PharmacyListInfoResponse(String address, String name, Double distance, String isOpen, String weekOfDay, String openTime,
                                    String closeTime) {
        this.address = address;
        this.name = name;
        this.distance = formatDistance(distance);
        this.isOpen = isOpen;
        this.weekOfDay = weekOfDay;
        this.openTime = formatTime(openTime);
        this.closeTime = formatTime(closeTime);
        this.message = null;
    }

    // 시간 포맷
    private String formatTime(String time) {
        if (time != null && time.length() == 4) {
            return time.substring(0, 2) + ":" + time.substring(2, 4);
        }
        return null;
    }

    // 거리 포맷
    public static Double formatDistance(Double distance) {
        if (distance != null && distance >= 0) {
            return Math.round(distance * 100.0) / 100.0;
        }
        return null;
    }
}
