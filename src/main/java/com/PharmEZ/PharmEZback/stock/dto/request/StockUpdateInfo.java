package com.PharmEZ.PharmEZback.stock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockUpdateInfo {

    private Long stockId;

    private Boolean isOutOfStock;
}
