package com.rsingla.fintech.lending.application;

import com.rsingla.fintech.lending.api.LoanApplicationRequest;
import com.rsingla.fintech.lending.domain.LoanApplication;
import com.rsingla.fintech.lending.infrastructure.LoanApplicationEntity;
import com.rsingla.fintech.lending.infrastructure.LoanApplicationRepository;
import com.rsingla.fintech.common.DomainException;
import java.time.Instant;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoanApplicationService {
    private final LoanDecisionPolicy decisionPolicy;
    private final LoanExplanationClient explanations;
    private final LoanApplicationRepository repository;

    public LoanApplicationService(LoanDecisionPolicy decisionPolicy, LoanExplanationClient explanations, LoanApplicationRepository repository) {
        this.decisionPolicy = decisionPolicy;
        this.explanations = explanations;
        this.repository = repository;
    }

    public LoanApplication submit(LoanApplicationRequest request) {
        var decision = decisionPolicy.evaluate(request);
        var explanation = explanations.explain(request, decision);
        var entity = LoanApplicationEntity.from(UUID.randomUUID().toString(), request, decision, explanation, Instant.now());
        return repository.save(entity).toDomain();
    }

    @Transactional(readOnly = true)
    public LoanApplication get(String id) {
        return repository.findById(id).orElseThrow(() -> new DomainException("Loan application not found: " + id)).toDomain();
    }

    @Transactional(readOnly = true)
    public Page<LoanApplication> list(Pageable pageable) {
        return repository.findAll(pageable).map(LoanApplicationEntity::toDomain);
    }
}
