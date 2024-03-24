package com.ecomerce.ms.service.order.api.rest;

import com.ecomerce.ms.service.order.api.rest.model.CreateCartRequest;
import com.ecomerce.ms.service.order.application.command.CommandGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "/api/carts")
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CommandGateway commandGateway;

    @PostMapping("/")
    public Callable<ResponseEntity<String>> createCart(@RequestBody CreateCartRequest request) {
        return () -> {
            return ResponseEntity.ok("Success");
        };
    }
}
