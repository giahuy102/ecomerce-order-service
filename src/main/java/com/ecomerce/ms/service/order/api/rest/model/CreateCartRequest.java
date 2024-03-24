package com.ecomerce.ms.service.order.api.rest.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class CreateCartRequest {
    @NotNull
    private UUID customerId;

    @NotEmpty
    private List<@Valid CartItem> items;
}
