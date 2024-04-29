package com.ecomerce.ms.service.order.domain.aggregate.cart;

import com.huyle.ms.domain.LocalEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Table(name = "cart_items", schema = "order_service")
@Entity
@Builder
public class CartItem extends LocalEntity<UUID> {
    @NotNull
    @Column(name = "product_id")
    private UUID productId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;
}
