package com.example.arup.personalaccount.DBHelper;

import android.widget.DatePicker;

public class IncomeExpenseJournalHelper {
    public static final String COL_TRANSID= "transId";
    public static final String COL_POSTINGDATE= "postingDate";
    public static final String COL_HEADID= "headId";
    public static final String COL_INCOMEAMOUNT= "incomeAmount";
    public static final String COL_EXPENSEAMOUNT=  "expenseAmount";
    public static final String COL_ACCOUNTTYPE= "accountTypeName";
    public static final String COL_PAYMENTMETHODID= "paymentMethodId";
    public static final String COL_BANKNAME="bankName";
    public static final String COL_ACCOUNTNAME= "accountName";
    public static final String COL_CHEQUENO= "chequeNo";
    public static final String COL_PAYMENTSTATUSID= "paymentStatusId";
    public static final String COL_DESCRIPTION= "description";
    public static final String COL_JOURNALREMARK="journalRemark";
    public static final String COL_REFERENCENUM= "refrenceNum";
    public static final String COL_CREATEDDATE= "createdate";
    public static final String COL_UPDATEDDATE= "updatedDate";


    public static final String TABLE_INCOMEEXPENSEJOURNAL="IncomeExpenseJournal";

    public static final String CREATE_TABLE_INCOMEEXPENSEJOURNAL=""+
            "CREATE TABLE "+TABLE_INCOMEEXPENSEJOURNAL+"("+
            COL_TRANSID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_POSTINGDATE+" TEXT,"+
            COL_HEADID+" INTEGER,"+
            COL_INCOMEAMOUNT+" REAL,"+
            COL_EXPENSEAMOUNT+" REAL,"+
            COL_ACCOUNTTYPE+" INTEGER,"+
            COL_PAYMENTMETHODID+" INTEGER,"+
            COL_BANKNAME+" INTEGER,"+
            COL_ACCOUNTNAME+" INTEGER,"+
            COL_CHEQUENO+" TEXT,"+
            COL_PAYMENTSTATUSID+" INTEGER,"+
            COL_DESCRIPTION+" TEXT,"+
            COL_JOURNALREMARK+" TEXT,"+
            COL_REFERENCENUM+" TEXT,"+
            COL_CREATEDDATE+" TEXT,"+
            COL_UPDATEDDATE+" TEXT"+
            ")";

}
