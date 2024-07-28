package com.ecomerce.ms.service.order.application.event;

import com.ecomerce.ms.service.order.application.saga.CreateOrderSaga;
import com.ecomerce.ms.service.order.domain.aggregate.order.OrderCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

    private final CreateOrderSaga createOrderSaga;

    @EventListener
    public void onOrderCreatedEvent(OrderCreated event) {
        createOrderSaga.buildSagaInstance(event.getOrder());
    }
}
