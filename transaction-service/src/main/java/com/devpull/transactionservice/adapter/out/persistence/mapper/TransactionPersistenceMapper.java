package com.devpull.transactionservice.adapter.out.persistence.mapper;

import com.devpull.transactionservice.adapter.out.persistence.entity.TransactionEntity;
import com.devpull.transactionservice.domain.model.Transaction;

public final class TransactionPersistenceMapper {

    private TransactionPersistenceMapper() {}

    public static TransactionEntity toEntity(Transaction domain) {
        return new TransactionEntity(
                domain.getId(),
                domain.getStatus(),
                domain.getType(),
                domain.getAmount(),
                domain.getCreatedAt(),
                domain.getUpdatedAt(),
                domain.getAccountId()
        );
    }

    public static Transaction toDomain(TransactionEntity entity) {
        return Transaction.rehydrate(
                entity.getId(),
                entity.getStatus(),
                entity.getType(),
                entity.getAmount(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getAccountId()
        );
    }
}
