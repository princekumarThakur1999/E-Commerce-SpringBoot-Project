package com.anoxi.repo;

import com.anoxi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findByEmail(String email);
}
