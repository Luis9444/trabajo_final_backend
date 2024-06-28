package com.ucc.crudorders.orders.service;

import com.ucc.crudorders.model.Order;
import com.ucc.crudorders.repositories.OrdersRepository;
import com.ucc.crudorders.service.OrdersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
 class OrdersServiceTest {
    @MockBean  
    private OrdersRepository ordersRepository;

    @Autowired   
    private OrdersService ordersService;

    @BeforeEach 
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
   void getOrders() { 
        Order ord = new Order(1L, "1000", "004", "100");
        List<Order> orders = Collections.singletonList(ord);
        when(ordersRepository.findAll()).thenReturn(orders);
        List<Order> result = ordersService.getOrders(); 
        assertEquals(1, result.size()); 
        assertEquals("004", result.get(0).getSku());
    }
    @Test
  void testDeleteOrders() { 
        Long ordersId = 1L; 
        ResponseEntity<Object> response = ordersService.deleteOrders(ordersId);
        verify(ordersRepository, times(1)).deleteById(ordersId); 
        assertEquals(HttpStatus.OK, response.getStatusCode()); 
        assertEquals("Orders delete successfully", response.getBody());
    }
}
