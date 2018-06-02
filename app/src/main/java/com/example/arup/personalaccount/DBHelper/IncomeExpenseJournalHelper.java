package com.example.arup.personalaccount.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.DatePicker;

import com.example.arup.personalaccount.Model.IncomeExpenseJournal;

import java.util.ArrayList;

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
    AccountHeadHelper accountHeadHelper;

    public IncomeExpenseJournalHelper(Context context) {

        databaseHelper = new DatabaseHelper(context);
        accountHeadHelper = new AccountHeadHelper(context);
    }

    public long insertIncomeExpenseJournal(IncomeExpenseJournal incomeExpenseJournal){
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //contentValues.put(COL_HEADID,incomeExpenseHead.getHeadId());
            contentValues.put(COL_POSTINGDATE,incomeExpenseJournal.getPostingDate().toString());
            contentValues.put(COL_HEADID,incomeExpenseJournal.getHeadId());
            contentValues.put(COL_INCOMEAMOUNT,incomeExpenseJournal.getIncomeAmount());
            contentValues.put(COL_EXPENSEAMOUNT,incomeExpenseJournal.getExpenseAmount());
            contentValues.put(COL_ACCOUNTTYPE,incomeExpenseJournal.getAccountTypeName());
            contentValues.put(COL_PAYMENTMETHODID,incomeExpenseJournal.getPaymentMethodId());
            contentValues.put(COL_BANKNAME,incomeExpenseJournal.getBankName());
            contentValues.put(COL_ACCOUNTNAME,incomeExpenseJournal.getAccountName());
            contentValues.put(COL_CHEQUENO,incomeExpenseJournal.getChequeNo());
            contentValues.put(COL_PAYMENTSTATUSID,incomeExpenseJournal.getPaymentStatusId());
            contentValues.put(COL_DESCRIPTION,incomeExpenseJournal.getDescription());
            contentValues.put(COL_JOURNALREMARK,incomeExpenseJournal.getJournalRemark());
            contentValues.put(COL_REFERENCENUM,incomeExpenseJournal.getRefrenceNum());
            contentValues.put(COL_CREATEDDATE,incomeExpenseJournal.getCreatedate().toString());
            contentValues.put(COL_UPDATEDDATE,incomeExpenseJournal.getUpdatedDate().toString());
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

    public ArrayList<IncomeExpenseJournal> getExpenseList(){
        try{
            database = databaseHelper.getReadableDatabase();
            String query = "SELECT  T0."+COL_TRANSID+", T0."+COL_POSTINGDATE+", T0."+COL_EXPENSEAMOUNT+
                    ", T0."+COL_JOURNALREMARK+", T1."+accountHeadHelper.COL_HEADNAME+"  FROM  "
                    +TABLE_INCOMEEXPENSEJOURNAL+" T0 INNER JOIN "+" "+accountHeadHelper.TABLE_ACCOUNTHEAD+
                    "  T1 ON  T0."+COL_HEADID+"=  T1."+accountHeadHelper.COL_HEADID+"  WHERE T0."+COL_ACCOUNTTYPE+"='Expense'";
//            Cursor cursor = database.query(TABLE_INCOMEEXPENSEJOURNAL,null,null,null,null,null,null);
            Cursor cursor = database.rawQuery(query,null);
            ArrayList<IncomeExpenseJournal> incomeExpenseJournalArrayList= new ArrayList<IncomeExpenseJournal>();

            if(cursor.moveToFirst()){
                do {
                    int transId = cursor.getInt(cursor.getColumnIndex(COL_TRANSID));
                    String postingdate = cursor.getString(cursor.getColumnIndex(COL_POSTINGDATE));
                    Double expenseAmount = cursor.getDouble(cursor.getColumnIndex(COL_EXPENSEAMOUNT));
                    String journalRemark = cursor.getString(cursor.getColumnIndex(COL_JOURNALREMARK));
                    String accHeadName = cursor.getString(cursor.getColumnIndex(accountHeadHelper.COL_HEADNAME));
                    //String accHeadName1 = cursor.getString(cursor.getColumnIndex(COL_HEADID));
                    IncomeExpenseJournal incomeExpenseJournal = new IncomeExpenseJournal(
                            transId,postingdate,expenseAmount,journalRemark,accHeadName
                    );
                    incomeExpenseJournalArrayList.add(incomeExpenseJournal);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return incomeExpenseJournalArrayList;

        }
        catch (Exception ex){
            throw ex;
        }
    }

}
