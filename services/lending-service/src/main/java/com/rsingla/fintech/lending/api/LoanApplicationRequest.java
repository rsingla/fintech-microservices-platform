package com.rsingla.fintech.lending.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record LoanApplicationRequest(
    @NotBlank String customerId,
    @NotNull @DecimalMin("1000.00") BigDecimal requestedAmount,
    @NotNull @DecimalMin("1.00") BigDecimal annualIncome,
    @NotNull @DecimalMin("0.00") BigDecimal monthlyDebt,
    @Min(300) @Max(850) int creditScore
) {
}
