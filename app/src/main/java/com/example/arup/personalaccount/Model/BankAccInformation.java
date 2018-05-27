package com.example.arup.personalaccount.Model;

public class BankAccInformation {
    private int accId;
    private int bankId;
    private String accName;
    private String branchName;

    public BankAccInformation() {
    }

    public BankAccInformation(int accId, int bankId, String accName) {
        this.accId = accId;
        this.bankId = bankId;
        this.accName = accName;
    }

    public BankAccInformation(int accId, int bankId, String accName, String branchName) {
        this.accId = accId;
        this.bankId = bankId;
        this.accName = accName;
        this.branchName = branchName;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
