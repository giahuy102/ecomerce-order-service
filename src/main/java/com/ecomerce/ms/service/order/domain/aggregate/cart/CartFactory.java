package com.ecomerce.ms.service.order.domain.aggregate.cart;

import com.ecomerce.ms.service.order.domain.shared.external.product.Product;
import com.ecomerce.ms.service.order.domain.shared.external.product.ProductGateway;
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

    public Cart createFrom(UUID customerId, List<CartItem> cartItems) {
        List<UUID> productIds = cartItems.stream()
                .map(CartItem::getProductId)
                .collect(Collectors.toList());
        List<Product> products = productGateway.getProducts(productIds);
        IntStream.range(0, cartItems.size())
                .forEach(idx -> {
                    Double productPrice = products.get(idx).getPriceUnit();
                    cartItems.get(idx).setPrice(productPrice);
                });
        return Cart.builder()
                .customerId(customerId)
                .items(cartItems)
                .build();
    }
}
