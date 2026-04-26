package com.rsingla.fintech.banking.application;

import com.rsingla.fintech.banking.api.AccountRequests.MoneyMovementRequest;
import com.rsingla.fintech.banking.api.AccountRequests.OpenAccountRequest;
import com.rsingla.fintech.banking.domain.BankAccount;
import com.rsingla.fintech.banking.infrastructure.AccountEntity;
import com.rsingla.fintech.banking.infrastructure.AccountRepository;
import com.rsingla.fintech.common.DomainException;
import com.rsingla.fintech.common.Money;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount open(OpenAccountRequest request) {
        var now = Instant.now();
        var entity = AccountEntity.open(UUID.randomUUID().toString(), request.customerId(), nextAccountNumber(), request.type(), now);
        return repository.save(entity).toDomain();
    }

    @Transactional(readOnly = true)
    public BankAccount get(String id) {
        return find(id).toDomain();
    }

    @Transactional(readOnly = true)
    public Page<BankAccount> list(Pageable pageable) {
        return repository.findAll(pageable).map(AccountEntity::toDomain);
    }

    public BankAccount deposit(String id, MoneyMovementRequest request) {
        var entity = find(id);
        entity.deposit(Money.usd(request.amount()), Instant.now());
        return repository.save(entity).toDomain();
    }

    public BankAccount withdraw(String id, MoneyMovementRequest request) {
        var entity = find(id);
        entity.withdraw(Money.usd(request.amount()), Instant.now());
        return repository.save(entity).toDomain();
    }

    private AccountEntity find(String id) {
        return repository.findById(id).orElseThrow(() -> new DomainException("Account not found: " + id));
    }

    private String nextAccountNumber() {
        return "90" + new BigDecimal(System.nanoTime()).abs().toPlainString();
    }
}
