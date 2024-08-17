package com.example.portfoliomanager.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "fund_balance_history")
@IdClass(FundBalanceHistory.FundBalanceHistoryId.class)
public class FundBalanceHistory {

    @Id
    @Column(name = "fund_manager_id", nullable = false)
    private Integer fundManagerId;

    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "balance_date", nullable = false)
    private String balanceDate;

    @Column(name = "balance")
    private Double balance;

    // Composite Key Class
    public static class FundBalanceHistoryId implements Serializable {
        private Integer fundManagerId;
        private String balanceDate;

        // Default constructor
        public FundBalanceHistoryId() {}

        // Constructor
        public FundBalanceHistoryId(Integer fundManagerId, String balanceDate) {
            this.fundManagerId = fundManagerId;
            this.balanceDate = balanceDate;
        }

        // Equals and HashCode methods
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FundBalanceHistoryId that = (FundBalanceHistoryId) o;
            return Objects.equals(fundManagerId, that.fundManagerId) &&
                    Objects.equals(balanceDate, that.balanceDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fundManagerId, balanceDate);
        }
    }

    // Getters and Setters
    public Integer getFundManagerId() {
        return fundManagerId;
    }

    public void setFundManagerId(Integer fundManagerId) {
        this.fundManagerId = fundManagerId;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(String balanceDate) {
        this.balanceDate = balanceDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
