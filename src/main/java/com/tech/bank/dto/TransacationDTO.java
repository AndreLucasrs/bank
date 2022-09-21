package com.tech.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tech.bank.model.OperationType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransacationDTO {

    @NotNull
    @JsonProperty("account_id")
    private Long accountId;

    @NotNull
    @JsonProperty("operation_type_id")
    private OperationType operationType;

    @NotNull
    private BigDecimal amount;

    public TransacationDTO(Long accountId, OperationType operationType, BigDecimal amount) {
        this.accountId = accountId;
        this.operationType = operationType;
        this.amount = amount;
    }

    public TransacationDTO() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
