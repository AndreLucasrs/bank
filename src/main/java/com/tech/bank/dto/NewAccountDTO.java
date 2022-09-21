package com.tech.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NewAccountDTO {

    @NotBlank
    @JsonProperty("document_number")
    private String documentNumber;

    public NewAccountDTO(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public NewAccountDTO() {
    }

    public String getDocumentNumber() {
        return documentNumber;
    }
}
