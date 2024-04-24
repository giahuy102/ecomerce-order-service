package com.ecomerce.ms.service.order.domain.aggregate.order;

import com.huyle.ms.domain.DomainEvent;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderCreated extends DomainEvent {
    private Order order;

    public OrderCreated(Order order) {
        super();
        this.order = order;
    }
}
