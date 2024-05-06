package com.ecomerce.ms.service.order.infrastructure.jpa.springdata;

import com.ecomerce.ms.service.order.domain.aggregate.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringCartRepository extends JpaRepository<Cart, UUID> {
}
