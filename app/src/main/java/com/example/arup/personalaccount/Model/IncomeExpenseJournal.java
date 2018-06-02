package com.example.arup.personalaccount.Model;

import android.widget.DatePicker;

import java.util.Date;

public class IncomeExpenseJournal {
    private int transId;
    private String postingDate;
    private int headId;
    private double incomeAmount;
    private double expenseAmount;
    private String accountTypeName;
    private String paymentMethodId;
    private String chequeNo;
    private String paymentStatusId;
    private String description;
    private String journalRemark;
    private String refrenceNum;
    private String createdate;
    private String updatedDate;

    private int bankName;
    private int accountName;

    private String headName;

    public int getBankName() {
        return bankName;
    }

    public void setBankName(int bankName) {
        this.bankName = bankName;
    }

    public int getAccountName() {
        return accountName;
    }

    public void setAccountName(int accountName) {
        this.accountName = accountName;
    }



    public IncomeExpenseJournal(int transId, String postingDate, int headId,
                                double incomeAmount, double expenseAmount, String accountTypeName,
                                String paymentMethodId, String chequeNo, String paymentStatusId, String description,
                                String journalRemark, String refrenceNum, String createdate,
                                String updatedDate, int bankName, int accountName) {
        this.transId = transId;
        this.postingDate = postingDate;
        this.headId = headId;
        this.incomeAmount = incomeAmount;
        this.expenseAmount = expenseAmount;
        this.accountTypeName = accountTypeName;
        this.paymentMethodId = paymentMethodId;
        this.chequeNo = chequeNo;
        this.paymentStatusId = paymentStatusId;
        this.description = description;
        this.journalRemark = journalRemark;
        this.refrenceNum = refrenceNum;
        this.createdate = createdate;
        this.updatedDate = updatedDate;
        this.bankName = bankName;
        this.accountName = accountName;
    }

    public IncomeExpenseJournal(String postingDate, int headId, double incomeAmount, double expenseAmount, String accountTypeName, String paymentMethodId, String chequeNo, String paymentStatusId, String description, String journalRemark, String refrenceNum, String createdate, String updatedDate, int bankName, int accountName) {
        this.postingDate = postingDate;
        this.headId = headId;
        this.incomeAmount = incomeAmount;
        this.expenseAmount = expenseAmount;
        this.accountTypeName = accountTypeName;
        this.paymentMethodId = paymentMethodId;
        this.chequeNo = chequeNo;
        this.paymentStatusId = paymentStatusId;
        this.description = description;
        this.journalRemark = journalRemark;
        this.refrenceNum = refrenceNum;
        this.createdate = createdate;
        this.updatedDate = updatedDate;
        this.bankName = bankName;
        this.accountName = accountName;
    }

    public IncomeExpenseJournal() {
    }

    public IncomeExpenseJournal(int transId, String postingDate, double expenseAmount, String journalRemark, String headName,double incomeAmont) {
        this.transId = transId;
        this.postingDate = postingDate;
        this.expenseAmount = expenseAmount;
        this.journalRemark = journalRemark;
        this.headName = headName;
        this.incomeAmount=incomeAmont;
    }



    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(String paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJournalRemark() {
        return journalRemark;
    }

    public void setJournalRemark(String journalRemark) {
        this.journalRemark = journalRemark;
    }

    public String getRefrenceNum() {
        return refrenceNum;
    }

    public void setRefrenceNum(String refrenceNum) {
        this.refrenceNum = refrenceNum;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }
}
