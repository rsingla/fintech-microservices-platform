package com.rsingla.fintech.banking.infrastructure;

import com.rsingla.fintech.banking.domain.AccountStatus;
import com.rsingla.fintech.banking.domain.AccountType;
import com.rsingla.fintech.banking.domain.BankAccount;
import com.rsingla.fintech.common.DomainException;
import com.rsingla.fintech.common.Money;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Table(name = "bank_accounts")
public class AccountEntity {
    @Id
    private String id;
    private String customerId;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private BigDecimal balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    protected AccountEntity() {
    }

    public static AccountEntity open(String id, String customerId, String accountNumber, AccountType type, Instant now) {
        var entity = new AccountEntity();
        entity.id = id;
        entity.customerId = customerId;
        entity.accountNumber = accountNumber;
        entity.type = type;
        entity.balance = BigDecimal.ZERO.setScale(2);
        entity.currency = "USD";
        entity.status = AccountStatus.ACTIVE;
        entity.createdAt = now;
        entity.updatedAt = now;
        return entity;
    }

    public void deposit(Money amount, Instant now) {
        ensureActive();
        this.balance = Money.usd(balance).add(amount).amount();
        this.updatedAt = now;
    }

    public void withdraw(Money amount, Instant now) {
        ensureActive();
        var nextBalance = Money.usd(balance).subtract(amount);
        if (nextBalance.isNegative()) {
            throw new DomainException("Withdrawal would overdraw the account");
        }
        this.balance = nextBalance.amount();
        this.updatedAt = now;
    }

    public BankAccount toDomain() {
        return new BankAccount(id, customerId, accountNumber, type, new Money(balance, Currency.getInstance(currency)), status, createdAt, updatedAt);
    }

    private void ensureActive() {
        if (status != AccountStatus.ACTIVE) {
            throw new DomainException("Account is not active");
        }
    }
}
