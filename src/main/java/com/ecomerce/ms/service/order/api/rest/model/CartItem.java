package com.ecomerce.ms.service.order.api.rest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

public class CartItem {
    @NotNull
    private UUID productId;

    @Positive
    private Integer quantity;
}
