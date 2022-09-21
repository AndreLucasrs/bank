package com.tech.bank.model;

import com.tech.bank.dto.NewAccountDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String documentNumber;

    public Account() {
    }

    public Account(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Account(NewAccountDTO newAccount) {
        this.documentNumber = newAccount.getDocumentNumber();
    }

    public Account(Long id, String documentNumber) {
        this.id = id;
        this.documentNumber = documentNumber;
    }

    public Long getId() {
        return id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }
}
