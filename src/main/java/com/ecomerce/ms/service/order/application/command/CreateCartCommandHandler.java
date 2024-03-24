package com.ecomerce.ms.service.order.application.command;

import com.ecomerce.ms.service.order.domain.aggregate.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCartCommandHandler implements CommandHandler<CreateCartCommand> {

    private final CartRepository cartRepository;

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
    }
}
