package com.rsingla.fintech.lending.infrastructure;

import com.rsingla.fintech.lending.api.LoanApplicationRequest;
import com.rsingla.fintech.lending.domain.DecisionResult;
import com.rsingla.fintech.lending.domain.LoanApplication;
import com.rsingla.fintech.lending.domain.LoanDecision;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "loan_applications")
public class LoanApplicationEntity {
    @Id
    private String id;
    private String customerId;
    private BigDecimal requestedAmount;
    private BigDecimal annualIncome;
    private BigDecimal monthlyDebt;
    private int creditScore;
    @Enumerated(EnumType.STRING)
    private LoanDecision decision;
    private String reasonCode;
    private String explanation;
    private Instant submittedAt;

    protected LoanApplicationEntity() {
    }

    public static LoanApplicationEntity from(String id, LoanApplicationRequest request, DecisionResult decision, String explanation, Instant submittedAt) {
        var entity = new LoanApplicationEntity();
        entity.id = id;
        entity.customerId = request.customerId();
        entity.requestedAmount = request.requestedAmount();
        entity.annualIncome = request.annualIncome();
        entity.monthlyDebt = request.monthlyDebt();
        entity.creditScore = request.creditScore();
        entity.decision = decision.decision();
        entity.reasonCode = decision.reasonCode();
        entity.explanation = explanation;
        entity.submittedAt = submittedAt;
        return entity;
    }

    public LoanApplication toDomain() {
        return new LoanApplication(id, customerId, requestedAmount, annualIncome, monthlyDebt, creditScore, decision, reasonCode, explanation, submittedAt);
    }
}
