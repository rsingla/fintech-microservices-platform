package com.rsingla.fintech.lending.domain;

public record DecisionResult(LoanDecision decision, String reasonCode, String rationale) {
}
