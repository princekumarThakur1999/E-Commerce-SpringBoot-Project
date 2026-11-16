package com.anoxi.repo;

import com.anoxi.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepo extends JpaRepository<OrderItems, Integer> {
}
