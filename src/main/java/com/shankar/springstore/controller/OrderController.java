package com.shankar.springstore.controller;

import com.shankar.springstore.dto.OrderDTO;
import com.shankar.springstore.model.Order;
import com.shankar.springstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(Authentication auth){
        String email = auth.getName();
        return orderService.placeOrder(email);
    }

    @GetMapping
    public List<Order> getOrders(Authentication auth){
        String email = auth.getName();
        return orderService.getOrders(email);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }


}

/*
/api/cart/add?productId=1&quantity=2
/api/cart
/api/orders/place
/api/orders
 */
