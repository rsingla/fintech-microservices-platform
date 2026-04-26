package com.rsingla.fintech.lending.domain;

import java.math.BigDecimal;
import java.time.Instant;

public record LoanApplication(
    String id,
    String customerId,
    BigDecimal requestedAmount,
    BigDecimal annualIncome,
    BigDecimal monthlyDebt,
    int creditScore,
    LoanDecision decision,
    String reasonCode,
    String explanation,
    Instant submittedAt
) {
}
