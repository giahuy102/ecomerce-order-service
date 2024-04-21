package com.ecomerce.ms.service.order.domain.aggregate.cart;

import com.huyle.ms.domain.DomainEvent;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartCreatedEvent extends DomainEvent {

    private Cart cart;

    public CartCreatedEvent(Cart cart) {
        super();
        this.cart = cart;
    }
}
