package com.shankar.springstore.dto;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private UserDTO user;
    private List<CartItemDTO> items;
    private double totalPrice;

    public OrderDTO(Long id, UserDTO user, List<CartItemDTO> items, double totalPrice) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
