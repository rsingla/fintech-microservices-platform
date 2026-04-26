package com.rsingla.fintech.banking.api;

import com.rsingla.fintech.banking.domain.AccountStatus;
import com.rsingla.fintech.banking.domain.AccountType;
import com.rsingla.fintech.banking.domain.BankAccount;
import java.math.BigDecimal;
import java.time.Instant;

public record AccountResponse(
    String id,
    String customerId,
    String accountNumber,
    AccountType type,
    BigDecimal balance,
    String currency,
    AccountStatus status,
    Instant createdAt,
    Instant updatedAt
) {
    static AccountResponse from(BankAccount account) {
        return new AccountResponse(account.id(), account.customerId(), account.accountNumber(), account.type(), account.balance().amount(), account.balance().currency().getCurrencyCode(), account.status(), account.createdAt(), account.updatedAt());
    }
}
