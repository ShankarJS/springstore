package com.shankar.springstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminSummaryDto {
    private long totalOrders;
    private double totalRevenue;
    private long totalUsers;
    private long totalProducts;
}
