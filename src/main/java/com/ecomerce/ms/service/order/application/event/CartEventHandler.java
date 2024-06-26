package com.ecomerce.ms.service.order.application.event;

import com.ecomerce.ms.service.order.domain.aggregate.cart.CartCreated;
import com.ecomerce.ms.service.order.domain.aggregate.order.Order;
import com.ecomerce.ms.service.order.domain.aggregate.order.OrderCreated;
import com.ecomerce.ms.service.order.domain.aggregate.order.OrderRepository;
import com.ecomerce.ms.service.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartEventHandler {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @EventListener
    void onCartCreatedEvent(CartCreated event) {
        Order newOrder = orderService.convertCartToOrder(event.getCart());
        newOrder.registerEvent(OrderCreated.builder()
                .order(newOrder)
                .build());
        orderRepository.save(newOrder);
    }
}
