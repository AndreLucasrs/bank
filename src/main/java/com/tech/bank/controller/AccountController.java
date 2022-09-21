package com.tech.bank.controller;

import com.tech.bank.dto.AccountDTO;
import com.tech.bank.dto.NewAccountDTO;
import com.tech.bank.service.CreateNewAccount;
import com.tech.bank.service.FindAccount;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final CreateNewAccount createNewAccount;
    private final FindAccount findAccount;

    public AccountController(CreateNewAccount createNewAccount, FindAccount findAccount) {
        this.createNewAccount = createNewAccount;
        this.findAccount = findAccount;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody NewAccountDTO newAccount) {
        createNewAccount.create(newAccount);
    }

    @GetMapping("/{accountId}")
    public AccountDTO find(@PathVariable Long accountId) {
        return findAccount.findById(accountId);
    }
}
