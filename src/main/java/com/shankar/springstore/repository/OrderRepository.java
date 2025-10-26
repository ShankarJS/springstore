package com.shankar.springstore.repository;

import com.shankar.springstore.dto.MonthlySalesDto;
import com.shankar.springstore.dto.TopProductDto;
import com.shankar.springstore.dto.UserStatsDto;
import com.shankar.springstore.model.Order;
import com.shankar.springstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    @Query("SELECT COUNT(o) FROM Order o")
    long countAllOrders();

    @Query("SELECT COALESCE(SUM(o.totalAmount),0) FROM Order o")
    double sumAllRevenue();

    @Query("SELECT new com.shankar.springstore.dto.MonthlySalesDto(YEAR(o.orderDate), MONTH(o.orderDate), COALESCE(SUM(o.totalAmount),0), COUNT(o)) " +
            "FROM Order o WHERE o.orderDate >= :from AND o.orderDate <= :to GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) ORDER BY YEAR(o.orderDate), MONTH(o.orderDate)")
    List<MonthlySalesDto> findMonthlySales(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    // Top products by quantity sold and revenue
    @Query("SELECT new com.shankar.springstore.dto.TopProductDto(oi.product.id, oi.product.name, COALESCE(SUM(oi.quantity),0), COALESCE(SUM(oi.price),0)) " +
            "FROM OrderItem oi WHERE oi.order.orderDate >= :from AND oi.order.orderDate <= :to GROUP BY oi.product.id, oi.product.name ORDER BY SUM(oi.quantity) DESC")
    List<TopProductDto> findTopProducts(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    // user stats
    @Query("SELECT new com.shankar.springstore.dto.UserStatsDto(u.id, u.email, u.name, COUNT(o), COALESCE(SUM(o.totalAmount),0)) " +
            "FROM Order o JOIN o.user u GROUP BY u.id, u.email, u.name ORDER BY SUM(o.totalAmount) DESC")
    List<UserStatsDto> findUserStats();
}

//Note: MONTH() and YEAR() JPQL functions are supported by many JPA providers but if your provider doesn’t support them, you can use FUNCTION('month', o.orderDate) style or a native query.

