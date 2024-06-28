package com.ucc.crudorders.service;

import com.ucc.crudorders.model.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class ProductService {

    public boolean isProductAvailable(String sku) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/api/products/" + sku;  
        try {
            ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
            return !response.getBody().getSku().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
