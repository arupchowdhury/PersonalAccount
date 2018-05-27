package com.example.arup.personalaccount.DBHelper;

public class BankInfoHelper {
    public static final String COL_BANKID="bankId";
    public static final String COL_BANKNAME= "bankName";

    public static final String TABLE_BANKINFO="BankInfo";

    public static final String CREATE_TABLE_BANKINFO=""+"("+
            COL_BANKID+" INTEGER PRIMARY KEY AUTOINCEMENT,"+
            COL_BANKNAME+" TEXT"+
            ")";
}
