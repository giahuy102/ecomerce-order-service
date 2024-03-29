package com.ecomerce.ms.service.order.domain.external.product;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Product {
    private UUID productId;
    private Double price;
}
