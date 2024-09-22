package com.ecomerce.ms.service.order.domain.shared.external.product;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Product {
    private UUID productId;
    private Double priceUnit;
    private Integer quantity;
    private UUID merchantUserId;
}
