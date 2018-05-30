package com.example.arup.personalaccount.Model;

import java.util.ArrayList;

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

    @Override
    public String toString() {
        return bankName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BankInformation){
            BankInformation c = (BankInformation )obj;
            if(c.getBankName().equals(bankName) && c.getBankId()==bankId ) return true;
        }

        return false;
    }

    public ArrayList<BankInformation> getBanklist(){
//        return new ArrayList<BankInformation>() {
//            new BankInformation(1, "Janata Bank"),
//                    new BankInformation(2, "Agroni Bank")
//        }
        ArrayList<BankInformation> bankInformationArrayList=new ArrayList<BankInformation>();
        bankInformationArrayList.add(new BankInformation(1,"Janata Bank"));
        bankInformationArrayList.add(new BankInformation(1,"Agrani Bank"));
        return bankInformationArrayList;
    }

}
