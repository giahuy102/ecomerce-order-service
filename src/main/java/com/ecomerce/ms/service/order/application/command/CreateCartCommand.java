package com.ecomerce.ms.service.order.application.command;

import com.ecomerce.ms.service.order.domain.aggregate.cart.CartItem;
import com.huyle.ms.command.Command;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateCartCommand implements Command {
    private UUID customerId;
    private List<CartItem> cartItems;
}
