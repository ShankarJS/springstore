package com.shankar.springstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopProductDto {
    private long productId;
    private String productName;
    private long quantitySold;
    private double revenue;
}
