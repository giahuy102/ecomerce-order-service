package com.ecomerce.ms.service.order.domain.aggregate.cart;

import com.huyle.ms.domain.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CartCreated extends DomainEvent {

    private Cart cart;

    public CartCreated(Cart cart) {
        super();
        this.cart = cart;
    }
}
