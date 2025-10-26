package com.shankar.springstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatsDto {
    private Long userId;
    private String email;
    private String name;
    private long ordersCount;
    private double lifetimeValue;
}
