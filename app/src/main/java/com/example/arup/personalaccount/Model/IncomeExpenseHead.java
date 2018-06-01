package com.example.arup.personalaccount.Model;

public class IncomeExpenseHead {
    private int headId;
    private String headName;
    private String headType;

    public IncomeExpenseHead() {
    }

    public IncomeExpenseHead(int headId, String headName) {
        this.headId = headId;
        this.headName = headName;
    }

    public IncomeExpenseHead(String headName, String headType) {
        this.headName = headName;
        this.headType = headType;
    }

    public IncomeExpenseHead(int headId, String headName, String headType) {
        this.headId = headId;
        this.headName = headName;
        this.headType = headType;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getHeadType() {
        return headType;
    }

    public void setHeadType(String headType) {
        this.headType = headType;
    }


    @Override
    public String toString() {
        return headName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BankInformation){
            BankInformation c = (BankInformation )obj;
            if(c.getBankName().equals(headName) && c.getBankId()==headId ) return true;
        }

        return false;
    }
}
