package com.tech.bank.service;

import com.tech.bank.exception.ResourceNotFoundException;
import com.tech.bank.model.Account;
import com.tech.bank.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindAccountTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private FindAccount findAccount;

    @Test
    @DisplayName("should find an account by id return dto")
    void findById() {

        var account = getAccount();
        when(repository.findById(any())).thenReturn(Optional.of(account));

        var accountDTO = findAccount.findById(1L);

        assertEquals(account.getDocumentNumber(), accountDTO.getDocumentNumber());
        verify(repository, times(1)).findById(any());
    }

    @Test
    @DisplayName("should find an account by id return model")
    void findAccountById() {

        var account = getAccount();
        when(repository.findById(any())).thenReturn(Optional.of(account));

        var result = findAccount.findAccountById(1L);

        assertEquals(account.getDocumentNumber(), result.getDocumentNumber());
        verify(repository, times(1)).findById(any());
    }


    @Test
    @DisplayName("shouldn't find an account by id")
    void notFindAccountById() {

        Assertions
                .assertThrows(ResourceNotFoundException.class, () -> {
                    findAccount.findAccountById(1L);
                }, "Account id not found !!");


        verify(repository, times(1)).findById(any());
    }

    private Account getAccount() {
        return new Account(1L, "12345678900");
    }
}