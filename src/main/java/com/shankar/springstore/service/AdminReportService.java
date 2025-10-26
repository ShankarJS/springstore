package com.shankar.springstore.service;

import com.shankar.springstore.dto.AdminSummaryDto;
import com.shankar.springstore.dto.MonthlySalesDto;
import com.shankar.springstore.dto.TopProductDto;
import com.shankar.springstore.dto.UserStatsDto;
import com.shankar.springstore.repository.OrderRepository;
import com.shankar.springstore.repository.ProductRepository;
import com.shankar.springstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminReportService {
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public AdminSummaryDto getSummary() {
        long totalOrders = orderRepo.countAllOrders();
        double totalRevenue = orderRepo.sumAllRevenue();
        long totalUsers = userRepo.count();
        long totalProducts = productRepo.count();
        return new AdminSummaryDto(totalOrders, totalRevenue, totalUsers, totalProducts);
    }

    public List<MonthlySalesDto> getMonthlySales(int monthsBack) {
        LocalDate now = LocalDate.now();
        LocalDate from = now.minusMonths(monthsBack - 1).withDayOfMonth(1);
        LocalDateTime fromDt = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime toDt = LocalDateTime.of(now.withDayOfMonth(now.lengthOfMonth()), LocalTime.MAX);
        return orderRepo.findMonthlySales(fromDt, toDt);
    }

    public List<TopProductDto> getTopProducts(int monthsBack, int limit) {
        LocalDate now = LocalDate.now();
        LocalDate from = now.minusMonths(monthsBack - 1).withDayOfMonth(1);
        LocalDateTime fromDt = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime toDt = LocalDateTime.of(now.withDayOfMonth(now.lengthOfMonth()), LocalTime.MAX);
        List<TopProductDto> list = orderRepo.findTopProducts(fromDt, toDt);
        if (limit > 0 && list.size() > limit) {
            return list.subList(0, limit);
        }
        return list;
    }

    public List<UserStatsDto> getUserStats() {
        return orderRepo.findUserStats();
    }

}
