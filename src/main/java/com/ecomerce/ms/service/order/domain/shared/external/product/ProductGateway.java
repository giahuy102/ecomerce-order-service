package com.ecomerce.ms.service.order.domain.shared.external.product;

import java.util.List;
import java.util.UUID;

public interface ProductGateway {
    public List<Product> getProducts(List<UUID> productIds);
}
