package com.ucc.crudorders.service;

import com.ucc.crudorders.model.Order;
import com.ucc.crudorders.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service 
@RequiredArgsConstructor 


public class OrdersService {

    private final OrdersRepository ordersRepository; 

    
    public List<Order> getOrders() {
        return ordersRepository.findAll();
    }


    
    public ResponseEntity<Object>  newOrders(Order orders) {
        ordersRepository.save(orders);
        return new ResponseEntity<>(
                HttpStatus.CREATED
        );
    }


    
    public ResponseEntity<Object> updateOrders(Long id,Order updatedOrders){ 

        Optional<Order> existingProductOptional = ordersRepository.findById(id); 

        if(existingProductOptional.isPresent()){ 

            Order existingProduct = existingProductOptional.get();
            existingProduct.setOrd(updatedOrders.getOrd());
            existingProduct.setSku(updatedOrders.getSku());
            existingProduct.setCant(updatedOrders.getCant());


            ordersRepository.save(existingProduct);
            return new ResponseEntity<>("Pedido updated successfully", HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Pedido not found" , HttpStatus.NOT_FOUND); 
        }

    }



    
    public ResponseEntity<Object> deleteOrders (Long id){
        ordersRepository.deleteById(id);
        return new ResponseEntity<>("Orders delete successfully", HttpStatus.OK);
    }
}
