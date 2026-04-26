package com.rsingla.fintech.lending.application;

import com.rsingla.fintech.lending.api.LoanApplicationRequest;
import com.rsingla.fintech.lending.domain.DecisionResult;

public interface LoanExplanationClient {
    String explain(LoanApplicationRequest request, DecisionResult decision);
}
