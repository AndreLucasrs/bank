package com.tech.bank.service;

import com.tech.bank.dto.NewAccountDTO;
import com.tech.bank.model.Account;
import com.tech.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateNewAccount {

    private final AccountRepository repository;

    public CreateNewAccount(AccountRepository repository) {
        this.repository = repository;
    }

    public void create(NewAccountDTO newAccount) {
        repository.save(new Account(newAccount));
    }
}
