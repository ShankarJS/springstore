package com.shankar.springstore.controller;

import com.shankar.springstore.model.Cart;
import com.shankar.springstore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public Cart addTocart(Authentication auth,
                          @RequestParam Long productId,
                          @RequestParam int quantity){
        String email =auth.getName();
        return cartService.addToCart(email, productId, quantity);
    }

    @GetMapping
    public Cart getCart(Authentication auth){
        String email = auth.getName();
        return cartService.getCart(email);
    }
}
