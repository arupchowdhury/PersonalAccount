package com.example.arup.personalaccount.Model;

import android.widget.DatePicker;

public class IncomeExpenseJournal {
    private int transId;
    private DatePicker postingDate;
    private int headId;
    private double incomeAmount;
    private double expenseAmount;
    private String accountTypeName;
    private int paymentMethodId;
    private String chequeNo;
    private int paymentStatusId;
    private String description;
    private String journalRemark;
    private String refrenceNum;
    private DatePicker createdate;
    private DatePicker updatedDate;

    private int bankName;
    private int accountName;

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



    public IncomeExpenseJournal(int transId, DatePicker postingDate, int headId,
                                double incomeAmount, double expenseAmount, String accountTypeName,
                                int paymentMethodId, String chequeNo, int paymentStatusId, String description,
                                String journalRemark, String refrenceNum, DatePicker createdate,
                                DatePicker updatedDate, int bankName, int accountName) {
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

    public IncomeExpenseJournal() {
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public DatePicker getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(DatePicker postingDate) {
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

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(int paymentStatusId) {
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

    public DatePicker getCreatedate() {
        return createdate;
    }

    public void setCreatedate(DatePicker createdate) {
        this.createdate = createdate;
    }

    public DatePicker getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(DatePicker updatedDate) {
        this.updatedDate = updatedDate;
    }
}
