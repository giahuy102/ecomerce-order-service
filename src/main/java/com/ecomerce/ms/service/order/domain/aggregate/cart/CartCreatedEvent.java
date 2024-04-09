package com.ecomerce.ms.service.order.domain.aggregate.cart;

import com.huyle.ms.domain.DomainEvent;

public class CartCreatedEvent extends DomainEvent<Cart> {
    public CartCreatedEvent(Cart cart) {
        super(cart);
    }
}
