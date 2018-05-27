package com.example.arup.personalaccount.Model;

public class PaymentMethod {
    private int methodId;
    private String methodName;

    public PaymentMethod() {
    }

    public PaymentMethod(int methodId, String methodName) {
        this.methodId = methodId;
        this.methodName = methodName;
    }

    public int getMethodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
