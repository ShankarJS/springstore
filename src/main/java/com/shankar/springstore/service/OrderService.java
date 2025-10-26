package com.shankar.springstore.service;

import com.shankar.springstore.dto.CartItemDTO;
import com.shankar.springstore.dto.OrderDTO;
import com.shankar.springstore.dto.ProductDTO;
import com.shankar.springstore.dto.UserDTO;
import com.shankar.springstore.model.*;
import com.shankar.springstore.repository.CartRepository;
import com.shankar.springstore.repository.OrderRepository;
import com.shankar.springstore.repository.ProductRepository;
import com.shankar.springstore.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartRepository cartRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return convertToDTO(order);
    }

    @Transactional
    public Order placeOrder(String email){
        User user = userRepo.findByEmail(email).
                orElseThrow(()->new RuntimeException("User not found"));

        Cart cart = cartRepo.findByUser(user).
                orElseThrow(()->new RuntimeException("Cart is empty"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(cart.getTotalPrice());

        for (CartItem ci: cart.getItems()){
            Product product = ci.getProduct();

            if(product.getStock()<ci.getQuantity())
                throw new RuntimeException("Insufficient stock for: "+product.getName());

            product.setStock(product.getStock() - ci.getQuantity());
            productRepo.save(product);

            OrderItem oi = OrderItem.builder().
                    order(order)
                    .product(product)
                    .quantity(ci.getQuantity())
                    .price(ci.getPrice())
                    .build();

            order.getItems().add(oi);
        }

        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepo.save(cart);

        return orderRepo.save(order);
    }

    public List<Order> getOrders(String email){
        User user = userRepo.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return orderRepo.findByUser(user);
    }

    public OrderDTO convertToDTO(Order order) {
        User user = order.getUser();
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());

        List<CartItemDTO> itemDTOs = order.getItems().stream().map(item ->
                new CartItemDTO(
                        item.getId(),
                        new ProductDTO(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getProduct().getDescription(),
                                item.getProduct().getPrice(),
                                item.getProduct().getCategory(),
                                item.getProduct().getImageUrl()
                        ),
                        item.getQuantity(),
                        item.getPrice()
                )
        ).collect(Collectors.toList());

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUser(userDTO);
        dto.setItems(itemDTOs);
        dto.setTotalPrice(order.getTotalAmount());
        return dto;
    }
}
