package com.ecomerce.ms.service.order.infrastructure.kafka;

import com.ecomerce.ms.service.CustomerVerificationReply;
import com.ecomerce.ms.service.SagaMetadata;
import com.ecomerce.ms.service.SagaStepStatusMessage;
import com.huyle.ms.saga.constant.SagaStepStatus;
import com.huyle.ms.saga.service.SagaProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.ecomerce.ms.service.order.domain.shared.Constants.CUSTOMER_VERIFICATION_REPLY_TOPIC;

@RequiredArgsConstructor
@Slf4j
@Component
public class OrderingSagaListener {

    private final SagaProvider sagaProvider;

    @KafkaListener(topics = CUSTOMER_VERIFICATION_REPLY_TOPIC)
    public void onCustomerVerificationReply(@Payload CustomerVerificationReply customerVerificationReply) {
        log.info("Receive event with message=\"{}\" from topic=\"{}\"", customerVerificationReply, CUSTOMER_VERIFICATION_REPLY_TOPIC);
        SagaMetadata sagaMetadata = customerVerificationReply.getSagaMetadata();
        sagaProvider.onStepEvent(sagaMetadata.getStepKey(), mapToSagaStepStatus(customerVerificationReply.getSagaStepStatus()), sagaMetadata.getInstanceId());
    }

    private SagaStepStatus mapToSagaStepStatus(SagaStepStatusMessage sagaStepStatusMessage) {
        return switch (sagaStepStatusMessage) {
            case SUCCEEDED -> SagaStepStatus.SUCCEEDED;
            case FAILED -> SagaStepStatus.FAILED;
            case COMPENSATED -> SagaStepStatus.COMPENSATED;
            case COMPENSATION_FAILED -> SagaStepStatus.COMPENSATION_FAILED;
            default -> throw new RuntimeException("Invalid kafka saga step status message");
        };
    }
}
