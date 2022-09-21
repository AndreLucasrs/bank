package com.tech.bank.model;

import com.tech.bank.config.OperationTypeConvertion;
import com.tech.bank.dto.TransacationDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account account;

    @NotNull
    @Convert(converter = OperationTypeConvertion.class)
    private OperationType operationType;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDateTime eventDate;

    public Transaction() {
    }

    public Transaction(Long id, Account account, OperationType operationType, BigDecimal amount, LocalDateTime eventDate) {
        this.id = id;
        this.account = account;
        this.operationType = operationType;
        this.amount = amount;
        this.eventDate = eventDate;
    }

    public Transaction(TransacationDTO transacation, Account account) {
        var operationType = transacation.getOperationType();
        this.account = account;
        this.operationType = operationType;
        this.amount = operationType.transactionAmount(transacation.getAmount());
        this.eventDate = LocalDateTime.now();
    }
}
