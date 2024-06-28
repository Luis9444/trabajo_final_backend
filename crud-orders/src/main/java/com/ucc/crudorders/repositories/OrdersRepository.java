package com.ucc.crudorders.repositories;

import com.ucc.crudorders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
