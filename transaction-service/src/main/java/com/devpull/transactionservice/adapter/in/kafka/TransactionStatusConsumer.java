package com.devpull.transactionservice.adapter.in.kafka;

import com.devpull.transactionservice.application.port.in.UpdateTransactionStatusUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionStatusConsumer {

    private final UpdateTransactionStatusUseCase useCase;

    public TransactionStatusConsumer(UpdateTransactionStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(TransactionStatusMessage message) {
        useCase.updateStatus(message.transactionId(), message.status())
                .subscribe(); // subscribe to trigger the execution
    }

    public record TransactionStatusMessage(UUID transactionId, String status) {}
}
