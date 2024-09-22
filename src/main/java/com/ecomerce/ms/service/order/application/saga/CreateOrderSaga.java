package com.ecomerce.ms.service.order.application.saga;

import com.ecomerce.ms.service.CustomerVerificationCommand;
import com.ecomerce.ms.service.InventoryProcessingCommand;
import com.ecomerce.ms.service.OrderItemMessage;
import com.ecomerce.ms.service.OrderMessage;
import com.ecomerce.ms.service.OrderingSagaKey;
import com.ecomerce.ms.service.SagaMetadata;
import com.ecomerce.ms.service.order.domain.aggregate.order.Order;
import com.huyle.ms.saga.entity.SagaInstance;
import com.huyle.ms.saga.entity.SagaStep;
import com.huyle.ms.saga.service.SagaProvider;
import com.huyle.ms.saga.service.SagaStepProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ecomerce.ms.service.order.domain.shared.Constants.CREATE_ORDER_SAGA;
import static com.ecomerce.ms.service.order.domain.shared.Constants.CUSTOMER_VERIFICATION_STEP;
import static com.ecomerce.ms.service.order.domain.shared.Constants.CUSTOMER_VERIFICATION_TOPIC;
import static com.ecomerce.ms.service.order.domain.shared.Constants.INVENTORY_PROCESSING_STEP;
import static com.ecomerce.ms.service.order.domain.shared.Constants.INVENTORY_PROCESSING_TOPIC;

@Component
@RequiredArgsConstructor
public class CreateOrderSaga {
    private final SagaProvider sagaProvider;
    private final SagaStepProvider sagaStepProvider;

    public void buildSagaInstance(Order order) {
        UUID instanceId = UUID.randomUUID(); // Saga's instanceId does not exists when not saving to database
        sagaProvider.initSagaInstance(instanceId, CREATE_ORDER_SAGA, buildSagaSteps(instanceId, order));
    }

    private List<SagaStep> buildSagaSteps(UUID instanceId, Order order) {
        OrderingSagaKey sagaKey = buildSagaKey(order.getId());
        return SagaInstance.stepBuilder()
                .step(sagaStepProvider.initSagaStep(CUSTOMER_VERIFICATION_STEP, sagaKey, buildCustomerVerificationCommand(instanceId, order), CUSTOMER_VERIFICATION_TOPIC, false))
                .step(sagaStepProvider.initSagaStep(INVENTORY_PROCESSING_STEP, sagaKey, buildInventoryProcessingCommand(instanceId, order), INVENTORY_PROCESSING_TOPIC,true))
                .build();
    }

    private CustomerVerificationCommand buildCustomerVerificationCommand(UUID instanceId, Order order) {
        return CustomerVerificationCommand.newBuilder()
                .setSagaMetadata(buildSagaMetadata(instanceId, CUSTOMER_VERIFICATION_STEP))
                .setCustomerId(order.getCustomerId())
                .setOrderId(order.getId())
                .build();
    }

    private InventoryProcessingCommand buildInventoryProcessingCommand(UUID instanceId, Order order) {
        return InventoryProcessingCommand.newBuilder()
                .setSagaMetadata(buildSagaMetadata(instanceId, INVENTORY_PROCESSING_STEP))
                .setOrder(buildOrderMessage(order))
                .build();
    }

    private OrderMessage buildOrderMessage(Order order) {
        List<OrderItemMessage> orderItems = order.getOrderItems().stream()
                        .map(item -> OrderItemMessage.newBuilder()
                                .setProductId(item.getProductId())
                                .setPrice(item.getPrice())
                                .setQuantity(item.getQuantity())
                                .setMerchantUserId(item.getMerchantUserId())
                                .build())
                        .collect(Collectors.toList());

        return OrderMessage.newBuilder()
                .setOrderId(order.getId())
                .setCustomerId(order.getCustomerId())
                .setOrderItems(orderItems)
                .build();
    }

    private OrderingSagaKey buildSagaKey(UUID orderId) {
        return OrderingSagaKey.newBuilder()
                .setOrderId(orderId)
                .build();
    }

    private SagaMetadata buildSagaMetadata(UUID instanceId, String stepKey) {
        return SagaMetadata.newBuilder()
                .setInstanceId(instanceId)
                .setStepKey(stepKey)
                .build();
    }
}
