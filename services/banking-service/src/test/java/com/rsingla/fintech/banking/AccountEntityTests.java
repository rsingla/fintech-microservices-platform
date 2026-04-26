package com.rsingla.fintech.banking;

import com.rsingla.fintech.banking.domain.AccountType;
import com.rsingla.fintech.banking.infrastructure.AccountEntity;
import com.rsingla.fintech.common.DomainException;
import com.rsingla.fintech.common.Money;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountEntityTests {
    @Test
    void withdrawalsCannotOverdrawAccount() {
        var account = AccountEntity.open("id", "customer", "9001", AccountType.CHECKING, Instant.now());
        assertThatThrownBy(() -> account.withdraw(Money.usd(BigDecimal.ONE), Instant.now()))
            .isInstanceOf(DomainException.class);
    }
}
