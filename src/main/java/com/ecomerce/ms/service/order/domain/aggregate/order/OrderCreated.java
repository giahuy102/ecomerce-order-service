package com.ecomerce.ms.service.order.domain.aggregate.order;

import com.huyle.ms.domain.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderCreated extends DomainEvent {
    private Order order;

    public OrderCreated(Order order) {
        super();
        this.order = order;
    }
}
