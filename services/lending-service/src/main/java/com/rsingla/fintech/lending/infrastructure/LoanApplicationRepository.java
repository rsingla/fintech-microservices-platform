package com.rsingla.fintech.lending.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplicationEntity, String> {
}
