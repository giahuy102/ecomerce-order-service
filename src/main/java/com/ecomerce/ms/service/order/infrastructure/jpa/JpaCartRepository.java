package com.ecomerce.ms.service.order.infrastructure.jpa;

import com.ecomerce.ms.service.order.domain.aggregate.cart.Cart;
import com.ecomerce.ms.service.order.domain.aggregate.cart.CartRepository;
import com.ecomerce.ms.service.order.infrastructure.jpa.springdata.SpringCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JpaCartRepository implements CartRepository {

    private final SpringCartRepository springCartRepository;

    public Cart save(Cart cart) {
        return springCartRepository.save(cart);
    }
}
