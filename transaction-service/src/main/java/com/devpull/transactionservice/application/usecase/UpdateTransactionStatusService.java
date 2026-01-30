package com.devpull.transactionservice.application.usecase;

import com.devpull.transactionservice.application.port.in.UpdateTransactionStatusUseCase;
import com.devpull.transactionservice.application.port.out.TransactionRepositoryPort;
import com.devpull.transactionservice.domain.enums.TransactionStatus;
import com.devpull.transactionservice.domain.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UpdateTransactionStatusService implements UpdateTransactionStatusUseCase {

    private final TransactionRepositoryPort txPort;

    public UpdateTransactionStatusService(TransactionRepositoryPort txPort) {
        this.txPort = txPort;
    }

    @Override
    public Mono<Void> updateStatus(UUID transactionId, String status) {
        TransactionStatus newStatus = TransactionStatus.valueOf(status.trim().toUpperCase());

        return txPort.findById(transactionId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Transaction", "id", transactionId)))
                .flatMap(tx -> {
                    tx.setStatus(newStatus);
                    return txPort.save(tx);
                })
                .then();
    }
}
