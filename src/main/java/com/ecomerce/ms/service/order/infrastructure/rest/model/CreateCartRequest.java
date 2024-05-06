package com.ecomerce.ms.service.order.infrastructure.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
public class CreateCartRequest {

    @JsonProperty(value = "customer_id")
    @NotNull
    private UUID customerId;

    @JsonProperty(value = "items")
    @NotEmpty
    private List<@Valid CartItemDTO> cartItems;
}
