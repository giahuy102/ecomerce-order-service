package com.ecomerce.ms.service.order.domain.service;

import com.ecomerce.ms.service.order.domain.aggregate.cart.Cart;
import com.ecomerce.ms.service.order.domain.aggregate.cart.CartItem;
import com.ecomerce.ms.service.order.domain.aggregate.order.Order;
import com.ecomerce.ms.service.order.domain.aggregate.order.OrderItem;
import com.ecomerce.ms.service.order.domain.aggregate.order.OrderStatus;
import com.ecomerce.ms.service.order.domain.aggregate.order.OrderStatusRepository;
import com.huyle.ms.domain.DomainException;
import com.huyle.ms.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.ecomerce.ms.service.order.domain.aggregate.order.OrderStatusName.OPEN;

@RequiredArgsConstructor
@Component
public class OrderService implements DomainService {

    private final OrderStatusRepository orderStatusRepository;

    public Order convertCartToOrder(Cart cart) {
        System.out.println("====================");
        System.out.println(cart.getItems().get(0).getPrice());
        OrderStatus openStatus = orderStatusRepository.findByStatusName(OPEN)
                .orElseThrow(() -> new DomainException("OPEN status not found"));

        Order convertedOrder = Order.builder()
                .customerId(cart.getCustomerId())
                .orderStatus(openStatus)
                .build();
        convertedOrder.setOrderItems(convertCartItemToOrderItem(cart.getItems(), convertedOrder));
        return convertedOrder;
    }

    private List<OrderItem> convertCartItemToOrderItem(List<CartItem> cartItems, Order order) {
        return cartItems.stream()
                .map(item -> OrderItem.builder()
                        .productId(item.getProductId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .merchantUserId(item.getMerchantUserId())
                        .order(order)
                        .build())
                .collect(Collectors.toList());
    }
}
