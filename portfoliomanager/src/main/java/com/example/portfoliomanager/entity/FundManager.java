package com.example.portfoliomanager.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "fund_manager")
public class FundManager {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer fund_manager_id;
    @Column(length=100, nullable=false)
    private String fm_name;
    @Column(columnDefinition = "decimal(12, 4) default 0", nullable = false)
    private double current_balance;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "datetime")
    private String date;

    public FundManager() {
    }

    public FundManager(int fund_manager_id, String fm_name, double current_balance, String date) {
        this.fund_manager_id = fund_manager_id;
        this.fm_name = fm_name;
        this.current_balance = current_balance;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Fund Manager{" +
                "fund_manager_id=" + fund_manager_id +
                ", name=" + fm_name +
                ", current_balance=" + current_balance +
                ", date=" + date +
                '}';
    }

    public int getFundManagerid() {
        return fund_manager_id;
    }

    public void setFundManagerid(int fund_manager_id) {
        this.fund_manager_id = fund_manager_id;
    }

    public String getFundManagerName() {
        return fm_name;
    }

    public void setFundManagerName(String fm_name) {
        this.fm_name = fm_name;
    }

    public double getCurrentBalance() {
        return current_balance;
    }

    public void setCurrentBalance(double current_balance) {
        this.current_balance = current_balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
