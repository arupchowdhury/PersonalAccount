package com.example.arup.personalaccount.DBHelper;

public class BankAccInfoHelper {
    public static final String COL_ACCID= "accId";
    public static final String COL_BANKID= "bankId";
    public static final String COL_ACCNAME= "accName";
    public static final String COL_BRANCHNAME= "branchName";

    public static final String TABLE_BANKACCINFO="BankAccInfo";

    public static final String CREATE_TABLE_BANKACCINFO=
            "CREATE TABLE "+TABLE_BANKACCINFO+"("+
            COL_ACCID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_BANKID+" INTEGER,"+
            COL_ACCNAME+" TEXT,"+
            COL_BRANCHNAME+" TEXT"+
            ")";
}
