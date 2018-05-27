package com.example.arup.personalaccount.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.arup.personalaccount.Model.IncomeExpenseHead;

public class AccountHeadHelper {
    public static final String COL_HEADID= "headid";
    public static final String COL_HEADNAME =  "headname";
    public static final String COL_HEADTYPE = "headtype";

    public static final String TABLE_ACCOUNTHEAD="accounthead";

    public static final String CREATE_TABLE_ACCOUNTHEAD="" +
            "CREATE TABLE"+TABLE_ACCOUNTHEAD +"("+
            COL_HEADID+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_HEADNAME+"TEXT,"+
            COL_HEADTYPE+"INTEGER"+
            ")"
            ;


    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public AccountHeadHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public long insertAccountHead(IncomeExpenseHead incomeExpenseHead){
        try{
            return (long) 1;
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
        return 0;
    }

    public long updateAccountHead(IncomeExpenseHead incomeExpenseHead){
        try{
            return (long) 1;
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
        return 0;
    }
}
