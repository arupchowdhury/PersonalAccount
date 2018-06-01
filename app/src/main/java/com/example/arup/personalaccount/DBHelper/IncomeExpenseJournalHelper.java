package com.example.arup.personalaccount.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.DatePicker;

import com.example.arup.personalaccount.Model.IncomeExpenseJournal;

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
            COL_ACCOUNTTYPE+" TEXT,"+
            COL_PAYMENTMETHODID+" TEXT,"+
            COL_BANKNAME+" INTEGER,"+
            COL_ACCOUNTNAME+" INTEGER,"+
            COL_CHEQUENO+" TEXT,"+
            COL_PAYMENTSTATUSID+" TEXT,"+
            COL_DESCRIPTION+" TEXT,"+
            COL_JOURNALREMARK+" TEXT,"+
            COL_REFERENCENUM+" TEXT,"+
            COL_CREATEDDATE+" TEXT,"+
            COL_UPDATEDDATE+" TEXT"+
            ")";


    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public IncomeExpenseJournalHelper(Context context) {

        databaseHelper = new DatabaseHelper(context);
    }

    public long insertIncomeExpenseJournal(IncomeExpenseJournal incomeExpenseJournal){
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //contentValues.put(COL_HEADID,incomeExpenseHead.getHeadId());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getPostingDate().toString());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getHeadId());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getIncomeAmount());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getExpenseAmount());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getAccountTypeName());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getPaymentMethodId());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getBankName());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getAccountName());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getChequeNo());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getPaymentStatusId());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getDescription());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getJournalRemark());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getRefrenceNum());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getCreatedate().toString());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getUpdatedDate().toString());
            long _id = database.insert(TABLE_INCOMEEXPENSEJOURNAL,null,contentValues);
            database.close();
            return _id;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return 0;
        }
    }

    public long updateIncomeExpenseJournal(IncomeExpenseJournal incomeExpenseJournal){
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_TRANSID,incomeExpenseJournal.getTransId());
//            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getPostingDate().toString());
//            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getHeadId());
//            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getIncomeAmount());
//            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getExpenseAmount());
//            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getAccountTypeName());
//            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getPaymentMethodId());
//            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getBankName());
//            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getAccountName());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getChequeNo());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getPaymentStatusId());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getDescription());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getJournalRemark());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getRefrenceNum());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getCreatedate().toString());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getUpdatedDate().toString());
            long _id = database.update(TABLE_INCOMEEXPENSEJOURNAL,contentValues,COL_TRANSID+"=?",new String[]{Integer.toString(incomeExpenseJournal.getTransId())});
            database.close();
            return _id;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return 0;
        }
    }

}
