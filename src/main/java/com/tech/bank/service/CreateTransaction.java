package com.tech.bank.service;

import com.tech.bank.dto.TransacationDTO;
import com.tech.bank.model.Transaction;
import com.tech.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTransaction {

    private final FindAccount findAccount;
    private final TransactionRepository transactionRepository;

    public CreateTransaction(FindAccount findAccount, TransactionRepository transactionRepository) {
        this.findAccount = findAccount;
        this.transactionRepository = transactionRepository;
    }

    public void create(TransacationDTO transacation) {
        var account = findAccount.findAccountById(transacation.getAccountId());

        transactionRepository.save(new Transaction(transacation, account));
    }
}
