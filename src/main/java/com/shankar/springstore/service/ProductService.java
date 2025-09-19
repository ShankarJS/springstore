package com.shankar.springstore.service;

import com.shankar.springstore.model.Product;
import com.shankar.springstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: "+id));
    }

    public Product updateProduct(Long id, Product updatedProduct){


    }



}
