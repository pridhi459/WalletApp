package com.bootcamp.walletapp.enums;

public enum CurrencyType {
    USD(1), EUR(0.92), INR(84);

    private double conversionRate;

    CurrencyType(double conversionRate){
        this.conversionRate = conversionRate;
    }

    public double getConversionRate(){
        return this.conversionRate;
    }
    public double convertToUSD(double amount){
        return amount * this.conversionRate;
    }
    public double convertFromUSD(double amount){
        return amount / this.conversionRate;
    }
}
