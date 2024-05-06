package com.ecomerce.ms.service.order.infrastructure.webclient;

import com.ecomerce.ms.service.order.domain.shared.external.product.Product;
import com.ecomerce.ms.service.order.domain.shared.external.product.ProductGateway;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Component
public class ProductGatewayImpl implements ProductGateway {

    private final WebClient webClient;

    public ProductGatewayImpl(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl("http://localhost:8081")
                .build();
    }

    public List<Product> getProducts(List<UUID> productIds) {
        return Flux.fromIterable(productIds)
                .flatMap(productId -> webClient.get()
                        .uri("/inventory-service/api/products/{id}", productId)
                        .retrieve()
                        .bodyToMono(Product.class))
                .collectList()
                .block();
    }
}
