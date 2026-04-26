package com.rsingla.fintech.lending.api;

import com.rsingla.fintech.common.PageResponse;
import com.rsingla.fintech.lending.application.LoanApplicationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loan-applications")
class LoanApplicationController {
    private final LoanApplicationService loans;

    LoanApplicationController(LoanApplicationService loans) {
        this.loans = loans;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    LoanApplicationResponse submit(@Valid @RequestBody LoanApplicationRequest request) {
        return LoanApplicationResponse.from(loans.submit(request));
    }

    @GetMapping("/{id}")
    LoanApplicationResponse get(@PathVariable String id) {
        return LoanApplicationResponse.from(loans.get(id));
    }

    @GetMapping
    PageResponse<LoanApplicationResponse> list(Pageable pageable) {
        var page = loans.list(pageable).map(LoanApplicationResponse::from);
        return new PageResponse<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
