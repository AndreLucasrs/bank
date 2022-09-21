package com.tech.bank.service;

import com.tech.bank.dto.NewAccountDTO;
import com.tech.bank.model.Account;
import com.tech.bank.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateNewAccountTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private CreateNewAccount createNewAccount;

    @Test
    @DisplayName("should create an account")
    void createAccount() {

        when(repository.save(any())).thenReturn(getAccount());

        createNewAccount.create(getNewAccountDto());

        verify(repository, times(1)).save(any());
    }

    private Account getAccount() {
        return new Account(1L, "12345678900");
    }

    private NewAccountDTO getNewAccountDto() {
        return new NewAccountDTO("12345678900");
    }
}