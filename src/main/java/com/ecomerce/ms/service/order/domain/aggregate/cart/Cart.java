package com.ecomerce.ms.service.order.domain.aggregate.cart;

import com.ecomerce.ms.service.order.domain.shared.BaseAggregateRoot;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Table(name = "carts")
@Entity
public class Cart extends BaseAggregateRoot<UUID> {
    @NotNull
    @Column(name = "customer_id")
    private UUID customerId;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
}
