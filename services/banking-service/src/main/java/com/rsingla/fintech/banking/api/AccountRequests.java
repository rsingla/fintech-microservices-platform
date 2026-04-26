package com.rsingla.fintech.banking.api;

import com.rsingla.fintech.banking.domain.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public final class AccountRequests {
    private AccountRequests() {
    }

    public record OpenAccountRequest(@NotBlank String customerId, @NotNull AccountType type) {
    }

    public record MoneyMovementRequest(@NotNull @DecimalMin("0.01") BigDecimal amount, String memo) {
    }
}
