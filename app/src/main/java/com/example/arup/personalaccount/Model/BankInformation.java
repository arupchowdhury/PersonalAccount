package com.example.arup.personalaccount.Model;

public class BankInformation {
    private int bankId;
    private String bankName;

    public BankInformation() {
    }

    public BankInformation(String bankName) {
        this.bankName = bankName;
    }

    public BankInformation(int bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
