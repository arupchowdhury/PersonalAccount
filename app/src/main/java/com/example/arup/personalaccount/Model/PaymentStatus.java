package com.example.arup.personalaccount.Model;

public class PaymentStatus {
    private  int statusId;
    private String statusName;

    public PaymentStatus() {
    }

    public PaymentStatus(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
