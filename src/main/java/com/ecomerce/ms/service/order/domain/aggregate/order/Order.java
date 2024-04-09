package com.ecomerce.ms.service.order.domain.aggregate.order;

import com.huyle.ms.domain.AggregateRoot;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Table(name = "orders")
@Entity
public class Order extends AggregateRoot<UUID> {
    @NotNull
    @Column(name = "customer_id")
    private UUID customerId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_approved_at")
    private Date orderApprovedAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_delivered_customer_date")
    private Date orderDeliveredCustomerDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
}
