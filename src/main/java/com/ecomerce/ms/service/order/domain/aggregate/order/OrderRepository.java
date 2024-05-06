package com.ecomerce.ms.service.order.domain.aggregate.order;

public interface OrderRepository {
    public Order save(Order order);
}
