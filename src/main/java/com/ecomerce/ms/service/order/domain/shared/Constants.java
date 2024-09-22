package com.ecomerce.ms.service.order.domain.shared;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String CUSTOMER_VERIFICATION_TOPIC = "customer.verification.event";

    public static final String CUSTOMER_VERIFICATION_REPLY_TOPIC = "customer.verification.reply";

    public static final String INVENTORY_PROCESSING_TOPIC = "inventory.processing.event";

    public static final String INVENTORY_PROCESSING_REPLY_TOPIC = "inventory.processing.reply";

    public static final String PAYMENT_PROCESSING_TOPIC = "payment.processing.event";

    public static final String PAYMENT_PROCESSING_REPLY_TOPIC = "payment.processing.reply";

    public static final String DELIVERY_PROCESSING_TOPIC = "delivery.processing.event";

    public static final String DELIVERY_PROCESSING_REPLY_TOPIC = "delivery.processing.reply";

    public static final String CREATE_ORDER_SAGA = "create_order_saga";

    public static final String CUSTOMER_VERIFICATION_STEP = "customer_verification_step";

    public static final String INVENTORY_PROCESSING_STEP = "inventory_processing_step";
}
