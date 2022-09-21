package com.tech.bank.config;

import com.tech.bank.model.OperationType;

import javax.persistence.AttributeConverter;

public class OperationTypeConvertion implements AttributeConverter<OperationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OperationType operationType) {
        return switch (operationType) {
            case SPOT_PURCHASE -> 1;
            case INSTALLMENT_PURCHASES -> 2;
            case WITHDRAW -> 3;
            case PAYMENT -> 4;
        };
    }

    @Override
    public OperationType convertToEntityAttribute(Integer value) {
        return switch (value) {
            case 1 -> OperationType.SPOT_PURCHASE;
            case 2 -> OperationType.INSTALLMENT_PURCHASES;
            case 3 -> OperationType.WITHDRAW;
            case 4 -> OperationType.PAYMENT;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }
}

