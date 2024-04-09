package com.ecomerce.ms.service.order.domain.aggregate.cart;

import com.huyle.ms.domain.AggregateRoot;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Table(name = "carts")
@Entity
public class Cart extends AggregateRoot<UUID> {
    @NotNull
    @Column(name = "customer_id")
    private UUID customerId;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

    // TODO: Add validation in constructor
}
