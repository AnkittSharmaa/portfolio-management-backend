package com.example.portfoliomanager.entity;

import java.math.BigDecimal;

public class CashFlowDTO {
    private String label; // Represents the transaction type (e.g., Income, Expenditure)
    private BigDecimal value; // Represents the total amount for the transaction type

    // Getters and Setters
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CashFlowDTO{" +
                "label='" + label + '\'' +
                ", value=" + value +
                '}';
    }
}

