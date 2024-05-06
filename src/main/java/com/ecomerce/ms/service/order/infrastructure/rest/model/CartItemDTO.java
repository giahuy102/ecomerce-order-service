package com.ecomerce.ms.service.order.infrastructure.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Getter
public class CartItemDTO {

    @JsonProperty(value = "product_id")
    @NotNull
    private UUID productId;

    @JsonProperty(value = "quantity")
    @Positive
    private Integer quantity;
}
