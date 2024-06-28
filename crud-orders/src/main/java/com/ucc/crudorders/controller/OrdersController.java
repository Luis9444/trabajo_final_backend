package com.ucc.crudorders.controller;

import com.ucc.crudorders.service.ProductService;
import com.ucc.crudorders.model.Order;
import com.ucc.crudorders.service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService; 
    private final ProductService productsProvider; 
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders() {
        return this.ordersService.getOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> newOrders (@Valid @RequestBody Order orders, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {           
            List<String> error = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()); 
            return  new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
        }
        if (productsProvider.isProductAvailable(orders.getSku())) {
            return ordersService.newOrders(orders);
        }else {
            return  new ResponseEntity<>("Producto no encontrado" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateOrders(@PathVariable("id")Long id,@RequestBody Order updateOrders){
        return ordersService.updateOrders(id,updateOrders);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteOrders(@PathVariable("id")Long id){
        return  ordersService.deleteOrders(id);
    }
}
