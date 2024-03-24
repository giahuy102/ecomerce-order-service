package com.ecomerce.ms.service.order.domain.aggregate.order;

import com.ecomerce.ms.service.order.domain.shared.BaseLocalEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Table(name = "order_items")
@Entity
public class OrderItem extends BaseLocalEntity<UUID> {
    @NotNull
    @Column(name = "product_id")
    private UUID productId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;
}
