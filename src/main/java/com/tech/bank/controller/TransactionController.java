package com.tech.bank.controller;

import com.tech.bank.dto.TransacationDTO;
import com.tech.bank.service.CreateTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final CreateTransaction createTransaction;

    public TransactionController(CreateTransaction createTransaction) {
        this.createTransaction = createTransaction;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody TransacationDTO transacation) {

        createTransaction.create(transacation);
    }
}
