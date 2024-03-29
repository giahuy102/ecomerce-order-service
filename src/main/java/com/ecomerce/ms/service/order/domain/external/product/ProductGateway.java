package com.ecomerce.ms.service.order.domain.external.product;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ProductGateway {
    public List<Product> getProducts(List<UUID> productIds);
}
