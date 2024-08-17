package com.example.portfoliomanager.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NetWorthDTO {
    private LocalDate txnDate;
    private BigDecimal netWorth;

    // Default constructor
    public NetWorthDTO() {}

    // Parameterized constructor
    public NetWorthDTO(LocalDate txnDate, BigDecimal netWorth) {
        this.txnDate = txnDate;
        this.netWorth = netWorth;
    }

    // Getters and setters
    public LocalDate getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(LocalDate txnDate) {
        this.txnDate = txnDate;
    }

    public BigDecimal getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(BigDecimal netWorth) {
        this.netWorth = netWorth;
    }
}
