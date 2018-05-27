package com.example.arup.personalaccount.Model;

public class IncomeExpenseHead {
    private int headId;
    private String headName;
    private int headType;

    public IncomeExpenseHead() {
    }

    public IncomeExpenseHead(int headId, String headName, int headType) {
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

    public int getHeadType() {
        return headType;
    }

    public void setHeadType(int headType) {
        this.headType = headType;
    }
}
