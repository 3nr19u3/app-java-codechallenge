package com.devpull.transactionservice.adapter.out.persistence.entity;

import com.devpull.transactionservice.domain.enums.AccountStatus;
import com.devpull.transactionservice.domain.enums.AccountType;
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
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private UUID id;

    @Column("status")
    private AccountStatus status;

    @Column("type")
    private AccountType type;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

}
