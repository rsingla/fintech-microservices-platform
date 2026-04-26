package com.rsingla.fintech.lending;

import com.rsingla.fintech.lending.api.LoanApplicationRequest;
import com.rsingla.fintech.lending.application.LoanDecisionPolicy;
import com.rsingla.fintech.lending.domain.LoanDecision;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoanDecisionPolicyTests {
    private final LoanDecisionPolicy policy = new LoanDecisionPolicy();

    @Test
    void approvesStrongApplication() {
        var decision = policy.evaluate(new LoanApplicationRequest("customer-1", new BigDecimal("10000"), new BigDecimal("120000"), new BigDecimal("800"), 740));
        assertThat(decision.decision()).isEqualTo(LoanDecision.APPROVED);
    }

    @Test
    void declinesLowCreditScore() {
        var decision = policy.evaluate(new LoanApplicationRequest("customer-1", new BigDecimal("10000"), new BigDecimal("120000"), new BigDecimal("800"), 580));
        assertThat(decision.reasonCode()).isEqualTo("CREDIT_SCORE_LOW");
    }
}
