package com.rsingla.fintech.banking.domain;

import com.rsingla.fintech.common.Money;
import java.time.Instant;

public record BankAccount(
    String id,
    String customerId,
    String accountNumber,
    AccountType type,
    Money balance,
    AccountStatus status,
    Instant createdAt,
    Instant updatedAt
) {
}
