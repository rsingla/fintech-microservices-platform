package com.rsingla.fintech.lending.application;

import com.rsingla.fintech.lending.api.LoanApplicationRequest;
import com.rsingla.fintech.lending.domain.DecisionResult;
import org.springframework.stereotype.Component;

@Component
class TemplateLoanExplanationClient implements LoanExplanationClient {
    @Override
    public String explain(LoanApplicationRequest request, DecisionResult decision) {
        return "Decision " + decision.decision() + " for customer " + request.customerId() + ": " + decision.rationale()
            + " Reason code: " + decision.reasonCode() + ". This deterministic explanation is the fallback boundary for Spring AI enrichment.";
    }
}
