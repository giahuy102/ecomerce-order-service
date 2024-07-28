package com.ecomerce.ms.service.order.application.command;

import com.ecomerce.ms.service.order.domain.aggregate.cart.Cart;
import com.ecomerce.ms.service.order.domain.aggregate.cart.CartCreated;
import com.ecomerce.ms.service.order.domain.aggregate.cart.CartFactory;
import com.ecomerce.ms.service.order.domain.aggregate.cart.CartRepository;
import com.huyle.ms.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCartCommandHandler implements CommandHandler<CreateCartCommand, Void> {

    private final CartRepository cartRepository;
    private final CartFactory cartFactory;

    @Transactional
    public Void handle(CreateCartCommand createCartCommand) {
        Cart cart = cartFactory.createFrom(createCartCommand.getCustomerId(), createCartCommand.getCartItems());
        cart.registerEvent(CartCreated.builder()
                .cart(cart)
                .build());
        cartRepository.save(cart);
        return null;
    }
}
