package com.ecomerce.ms.service.order.infrastructure.rest;

import com.ecomerce.ms.service.order.application.command.CreateCartCommand;
import com.ecomerce.ms.service.order.infrastructure.mapper.CartMapper;
import com.ecomerce.ms.service.order.infrastructure.rest.model.CreateCartRequest;
import com.huyle.ms.command.CommandGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CommandGateway commandGateway;
    private final CartMapper cartMapper;

    @PostMapping("/api/carts")
    public Callable<ResponseEntity<String>> submitCart(@RequestBody CreateCartRequest request) {
        CreateCartCommand createCartCommand = cartMapper.toCreateCartCommand(request);
        commandGateway.handle(createCartCommand);
        return () -> {
            return ResponseEntity.ok("Success");
        };
    }
}
