package com.anoxi.repo;

import com.anoxi.entities.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepo extends JpaRepository<ShippingAddress, Integer> {
}
