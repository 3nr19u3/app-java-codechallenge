package com.devpull.transactionservice.adapter.out.persistence.entity;

import com.devpull.transactionservice.domain.enums.TransactionStatus;
import com.devpull.transactionservice.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    private UUID id;

    @Column("status")
    private TransactionStatus status;

    @Column("type")
    private TransactionType type;

    @Column("amount")
    private double amount;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

    @Column("account_id")
    private UUID accountId;
}
