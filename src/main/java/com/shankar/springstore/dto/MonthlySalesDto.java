package com.shankar.springstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySalesDto {
    private int year;
    private int month;
    private double totalAmount;
    private long orderCount;

}
