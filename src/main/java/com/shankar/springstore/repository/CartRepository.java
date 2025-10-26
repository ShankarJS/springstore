package com.shankar.springstore.repository;

import com.shankar.springstore.model.Cart;
import com.shankar.springstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
