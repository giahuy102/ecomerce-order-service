package com.ecomerce.ms.service.order.domain.aggregate.order;

import com.huyle.ms.domain.LocalEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Table(name = "order_statuses")
@Entity
public class OrderStatus extends LocalEntity<Integer> {

    public OrderStatus() {
        super();
    }

    @NotNull
    @Size(max = 255)
    @Column(name = "status_name", unique = true)
    @Enumerated(EnumType.STRING)
    private OrderStatusName statusName;

    @NotNull
    @Size(min = 7, max = 7)
    @Pattern(regexp = "^#[0-9a-fA-F]{6}$")
    @Column(name = "color")
    private String color;

    @Column(name = "created_by_staff_id")
    private UUID createdByStaffId;

    @Column(name = "updated_by_staff_id")
    private UUID updatedByStaffId;
}
