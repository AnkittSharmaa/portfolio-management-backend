package com.example.portfoliomanager.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "funds")
public class FundTran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Integer recId;

    @Column(length = 1, name = "tran_type")
    private String tranType;

    @Column(name = "amount")
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tran_time")
    private String tranTime;

    @Column(name = "fund_manager_id")
    private Integer fundManagerId;

    @Column(name = "fund_acc_balance")
    private Double fundAccBalance;

    @Column(length = 50, name = "bank_acc_no")
    private String bankAccNo;

    // Getters and Setters
    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public Integer getFundManagerId() {
        return fundManagerId;
    }

    public void setFundManagerId(Integer fundManagerId) {
        this.fundManagerId = fundManagerId;
    }

    public Double getFundAccBalance() {
        return fundAccBalance;
    }

    public void setFundAccBalance(Double fundAccBalance) {
        this.fundAccBalance = fundAccBalance;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

};