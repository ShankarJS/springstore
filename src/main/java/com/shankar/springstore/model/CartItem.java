package com.shankar.springstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude      //prevent recursion
    @JsonBackReference
//    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private int quantity;
    private double price;

}
