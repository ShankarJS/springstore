package com.shankar.springstore.service;

import com.shankar.springstore.model.Cart;
import com.shankar.springstore.model.CartItem;
import com.shankar.springstore.model.Product;
import com.shankar.springstore.model.User;
import com.shankar.springstore.repository.CartRepository;
import com.shankar.springstore.repository.ProductRepository;
import com.shankar.springstore.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    @Transactional
    public Cart addToCart(String email, Long productId, int quantity){
        User user = userRepo.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(()-> new RuntimeException(("Product not found")));

        if(product.getStock() < quantity){
            throw new RuntimeException("Insufficient stock for product: "+product.getName());
        }

        Cart cart = cartRepo.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepo.save(newCart);
        });

        Optional<CartItem> existingItem = cart.getItems().stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst();

        if(existingItem.isPresent()){
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            item.setPrice(item.getQuantity() * product.getPrice());
        } else{
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .price(quantity*product.getPrice())
                    .build();
            cart.getItems().add(newItem);
        }

        double total = cart.getItems().stream().mapToDouble(CartItem::getPrice).sum();
        cart.setTotalPrice(total);
        return cartRepo.save(cart);
    }

    public Cart getCart(String email){
        User user = userRepo.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return cartRepo.findByUser(user).orElseThrow(()->new RuntimeException("Cart not found"));
    }
}
