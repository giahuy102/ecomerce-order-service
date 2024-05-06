package com.ecomerce.ms.service.order.infrastructure.jpa;

import com.ecomerce.ms.service.order.domain.aggregate.order.Order;
import com.ecomerce.ms.service.order.domain.aggregate.order.OrderRepository;
import com.ecomerce.ms.service.order.infrastructure.jpa.springdata.SpringOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JpaOrderRepository implements OrderRepository {

    private final SpringOrderRepository springOrderRepository;

    public Order save(Order order) {
        return springOrderRepository.save(order);
    }
}
