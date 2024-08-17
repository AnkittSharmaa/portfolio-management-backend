package com.example.portfoliomanager.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    private String symbol;

    private String asset_type;

    private String asset_name;

    private double closing_price;

    // Constructors
    public Asset() {
    }

    public Asset(String symbol, String asset_type, String asset_name, double closing_price) {
        this.symbol = symbol;
        this.asset_type = asset_type;
        this.asset_name = asset_name;
        this.closing_price = closing_price;
    }

    // Getters and Setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAssetType() {
        return asset_type;
    }

    public void setAssetType(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getAssetName() {
        return asset_name;
    }

    public void setAssetName(String asset_name) {
        this.asset_name = asset_name;
    }

    public double getClosingPrice() {
        return closing_price;
    }

    public void setClosingPrice(double closing_price) {
        this.closing_price = closing_price;
    }
}