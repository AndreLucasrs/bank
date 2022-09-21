package com.tech.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tech.bank.model.Account;

public class AccountDTO {

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("document_number")
    private String documentNumber;

    public AccountDTO(Account account) {
        this.accountId = account.getId();
        this.documentNumber = account.getDocumentNumber();
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }
}
