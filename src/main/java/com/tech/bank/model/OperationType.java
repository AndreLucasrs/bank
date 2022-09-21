package com.tech.bank.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

public enum OperationType {

    SPOT_PURCHASE(1){
        @Override
        public BigDecimal transactionAmount(BigDecimal value) {
            return value.negate();
        }
    },
    INSTALLMENT_PURCHASES(2){
        @Override
        public BigDecimal transactionAmount(BigDecimal value) {
            return value.negate();
        }
    },
    WITHDRAW(3){
        @Override
        public BigDecimal transactionAmount(BigDecimal value) {
            return value.negate();
        }
    },
    PAYMENT(4){
        @Override
        public BigDecimal transactionAmount(BigDecimal value) {
            return value;
        }
    };

    private Integer operationTypeId;

    OperationType(Integer operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    @JsonValue
    public Integer getOperationTypeId() {
        return operationTypeId;
    }

    public abstract BigDecimal transactionAmount(BigDecimal value);
}
