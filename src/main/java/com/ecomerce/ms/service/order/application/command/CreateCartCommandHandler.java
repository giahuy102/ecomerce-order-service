package com.ecomerce.ms.service.order.application.command;

import com.ecomerce.ms.service.order.domain.aggregate.cart.Cart;
import com.ecomerce.ms.service.order.domain.aggregate.cart.CartCreatedEvent;
import com.ecomerce.ms.service.order.domain.aggregate.cart.CartFactory;
import com.ecomerce.ms.service.order.domain.aggregate.cart.CartRepository;
import com.huyle.ms.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCartCommandHandler implements CommandHandler<CreateCartCommand> {

    private final CartRepository cartRepository;
    private final CartFactory cartFactory;

    @Transactional
    public void handle(CreateCartCommand createCartCommand) {
        /*
            Where to put entity in another bounded context (microservice)?????
            What is passed to CartFactory?
            How WebClient is called?
            Call from Service or gateway? If from service, where to put service?
                Each method in gateway is implemented in infra layer
                Gateway only calls external service
                Service use gateway + include business logic
            Kafka publisher and subscriber?
            Mono and Flux in WebClient reactive programming?

         */
        Cart cart = cartFactory.createFrom(createCartCommand.getCustomerId(), createCartCommand.getCartItems());
        cart.registerEvent(CartCreatedEvent.builder()
                .cart(cart)
                .build());
        cartRepository.save(cart);
    }
}
