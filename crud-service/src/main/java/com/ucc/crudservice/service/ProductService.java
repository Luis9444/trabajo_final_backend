package com.ucc.crudservice.service;

import com.ucc.crudservice.model.Product;
import com.ucc.crudservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository productRepository; 

    
    public List<Product> getProducts (){
        return  productRepository.findAll();
    }

    
    public ResponseEntity<Object>  newProduct(Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(
                HttpStatus.CREATED
        );
    }

public ResponseEntity<Object> updateProduct(Long id,Product updatedProduct){ 

    Optional<Product>existingProductOptional = productRepository.findById(id); 

    if(existingProductOptional.isPresent()){ 

        Product existingProduct = existingProductOptional.get();
        existingProduct.setSku(updatedProduct.getSku());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStatus(updatedProduct.getStatus());


        productRepository.save(existingProduct);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);

    }else {
        return new ResponseEntity<>("product not found" , HttpStatus.NOT_FOUND); 
    }

    }


    
    public ResponseEntity<Object> deleteProduct (Long id){
        productRepository.deleteById(id);
        return new ResponseEntity<>("Product delete successfully", HttpStatus.OK);
    }


    public Product getProduct (String sku){
        return productRepository.findBySku(sku);
}

}
