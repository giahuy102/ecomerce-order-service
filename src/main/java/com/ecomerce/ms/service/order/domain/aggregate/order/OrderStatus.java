package com.ecomerce.ms.service.order.domain.aggregate.order;

import com.huyle.ms.domain.LocalEntity;
import lombok.Getter;

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
@Table(name = "order_statuses")
@Entity
public class OrderStatus extends LocalEntity<Integer> {
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

    @NotNull
    @Column(name = "created_by_staff_id")
    private UUID createdByStaffId;

    @NotNull
    @Column(name = "updated_by_staff_id")
    private UUID updatedByStaffId;
}
