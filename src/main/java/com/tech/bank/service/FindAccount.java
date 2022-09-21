package com.tech.bank.service;

import com.tech.bank.dto.AccountDTO;
import com.tech.bank.exception.ResourceNotFoundException;
import com.tech.bank.model.Account;
import com.tech.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class FindAccount {

    private final AccountRepository repository;

    public FindAccount(AccountRepository repository) {
        this.repository = repository;
    }

    public AccountDTO findById(Long id) {
        var account = findAccountById(id);
        return new AccountDTO(account);
    }

    public Account findAccountById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account id not found !!"));
    }
}
