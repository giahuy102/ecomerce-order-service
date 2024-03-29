package com.ecomerce.ms.service.order.domain.aggregate.cart;

import com.ecomerce.ms.service.order.domain.external.product.Product;
import com.ecomerce.ms.service.order.domain.external.product.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class CartFactory {

    private final ProductGateway productGateway;

    public Cart createFrom(UUID customerId, List<CartItem> items) {
        List<UUID> productIds = items.stream()
                .map(CartItem::getId)
                .collect(Collectors.toList());
        List<Product> products = productGateway.getProducts(productIds);
        IntStream.range(0, items.size())
                .forEach(idx -> {
                    Double productPrice = products.get(idx).getPrice();
                    items.get(idx).setPrice(productPrice);
                });
        return Cart.builder()
                .customerId(customerId)
                .items(items)
                .build();
    }
}
