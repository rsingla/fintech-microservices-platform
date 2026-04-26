package com.rsingla.fintech.banking.api;

import com.rsingla.fintech.banking.api.AccountRequests.MoneyMovementRequest;
import com.rsingla.fintech.banking.api.AccountRequests.OpenAccountRequest;
import com.rsingla.fintech.banking.application.AccountService;
import com.rsingla.fintech.common.PageResponse;
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
@RequestMapping("/api/v1/accounts")
class AccountController {
    private final AccountService accounts;

    AccountController(AccountService accounts) {
        this.accounts = accounts;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountResponse open(@Valid @RequestBody OpenAccountRequest request) {
        return AccountResponse.from(accounts.open(request));
    }

    @GetMapping("/{id}")
    AccountResponse get(@PathVariable String id) {
        return AccountResponse.from(accounts.get(id));
    }

    @GetMapping
    PageResponse<AccountResponse> list(Pageable pageable) {
        var page = accounts.list(pageable).map(AccountResponse::from);
        return new PageResponse<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    @PostMapping("/{id}/deposits")
    AccountResponse deposit(@PathVariable String id, @Valid @RequestBody MoneyMovementRequest request) {
        return AccountResponse.from(accounts.deposit(id, request));
    }

    @PostMapping("/{id}/withdrawals")
    AccountResponse withdraw(@PathVariable String id, @Valid @RequestBody MoneyMovementRequest request) {
        return AccountResponse.from(accounts.withdraw(id, request));
    }
}
