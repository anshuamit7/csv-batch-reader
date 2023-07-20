package com.spring.batch.repository;

import com.spring.batch.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String> {

}
