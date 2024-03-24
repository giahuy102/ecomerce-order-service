package com.ecomerce.ms.service.order.application.command;

import com.ecomerce.ms.service.order.domain.aggregate.cart.CartItem;

import java.util.List;
import java.util.UUID;

public class CreateCartCommand implements Command {
    private UUID customerId;
    private List<CartItem> cartItems;
}
