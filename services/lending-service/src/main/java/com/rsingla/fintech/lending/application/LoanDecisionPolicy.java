package com.rsingla.fintech.lending.application;

import com.rsingla.fintech.lending.api.LoanApplicationRequest;
import com.rsingla.fintech.lending.domain.DecisionResult;
import com.rsingla.fintech.lending.domain.LoanDecision;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class LoanDecisionPolicy {
    public DecisionResult evaluate(LoanApplicationRequest request) {
        if (request.creditScore() < 620) {
            return new DecisionResult(LoanDecision.DECLINED, "CREDIT_SCORE_LOW", "Credit score is below the platform's minimum threshold.");
        }
        var monthlyIncome = request.annualIncome().divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_EVEN);
        var debtToIncome = request.monthlyDebt().divide(monthlyIncome, 4, RoundingMode.HALF_EVEN);
        if (debtToIncome.compareTo(new BigDecimal("0.45")) > 0) {
            return new DecisionResult(LoanDecision.DECLINED, "DTI_TOO_HIGH", "Debt-to-income ratio is above the approved policy range.");
        }
        if (request.requestedAmount().compareTo(request.annualIncome().multiply(new BigDecimal("0.50"))) > 0) {
            return new DecisionResult(LoanDecision.REFER, "MANUAL_REVIEW_AMOUNT", "Requested amount is large relative to annual income and needs review.");
        }
        return new DecisionResult(LoanDecision.APPROVED, "POLICY_APPROVED", "Application satisfies credit score, affordability, and exposure rules.");
    }
}
