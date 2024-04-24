package com.ecomerce.ms.service.order.application.event;

import com.ecomerce.ms.service.Metadata;
import com.ecomerce.ms.service.OrderCreatedEvent;
import com.ecomerce.ms.service.order.domain.aggregate.order.OrderCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

    private final KafkaTemplate<String, OrderCreatedEvent> orderCreatedTemplate;

    @EventListener
    public void onOrderCreatedEvent(OrderCreated event) {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.newBuilder()
                .setMetadata(buildMetadata())
                .setOrderId(event.getOrder().getId())
                .setCustomerId(event.getOrder().getCustomerId())
                .build();
        orderCreatedTemplate.send("order.created", orderCreatedEvent);
    }

    public Metadata buildMetadata() {
        return Metadata.newBuilder()
                .setSubmitTime(System.currentTimeMillis())
                .build();
    }
}
