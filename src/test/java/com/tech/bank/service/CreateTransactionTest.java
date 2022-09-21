package com.tech.bank.service;

import com.tech.bank.dto.TransacationDTO;
import com.tech.bank.model.Account;
import com.tech.bank.model.OperationType;
import com.tech.bank.model.Transaction;
import com.tech.bank.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTransactionTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private FindAccount findAccount;

    @InjectMocks
    private CreateTransaction createTransaction;

    @Test
    @DisplayName("should create a transaction")
    void createTransaction() {

        when(findAccount.findAccountById(any())).thenReturn(getAccount());
        when(repository.save(any())).thenReturn(getTransaction());

        createTransaction.create(getTransactionDto());

        verify(findAccount, times(1)).findAccountById(any());
        verify(repository, times(1)).save(any());
    }

    private Account getAccount() {
        return new Account(1L, "12345678900");
    }

    private TransacationDTO getTransactionDto() {
        return new TransacationDTO(1L, OperationType.PAYMENT, new BigDecimal(123));
    }

    private Transaction getTransaction() {
        return new Transaction(getTransactionDto(), getAccount());
    }
}