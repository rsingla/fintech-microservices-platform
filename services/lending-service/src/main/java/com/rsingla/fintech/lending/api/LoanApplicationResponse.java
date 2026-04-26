package com.rsingla.fintech.lending.api;

import com.rsingla.fintech.lending.domain.LoanApplication;
import com.rsingla.fintech.lending.domain.LoanDecision;
import java.math.BigDecimal;
import java.time.Instant;

public record LoanApplicationResponse(
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
    static LoanApplicationResponse from(LoanApplication application) {
        return new LoanApplicationResponse(application.id(), application.customerId(), application.requestedAmount(), application.annualIncome(), application.monthlyDebt(), application.creditScore(), application.decision(), application.reasonCode(), application.explanation(), application.submittedAt());
    }
}
