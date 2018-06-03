package com.example.arup.personalaccount.DBHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PersonalAcc";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AccountHeadHelper.CREATE_TABLE_ACCOUNTHEAD);
        db.execSQL(BankInfoHelper.CREATE_TABLE_BANKINFO);
        db.execSQL(BankAccInfoHelper.CREATE_TABLE_BANKACCINFO);
        db.execSQL(IncomeExpenseJournalHelper.CREATE_TABLE_INCOMEEXPENSEJOURNAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ AccountHeadHelper.TABLE_ACCOUNTHEAD);
        db.execSQL(AccountHeadHelper.CREATE_TABLE_ACCOUNTHEAD);

        db.execSQL("DROP TABLE IF EXISTS "+ BankInfoHelper.TABLE_BANKINFO);
        db.execSQL(BankInfoHelper.CREATE_TABLE_BANKINFO);

        db.execSQL("DROP TABLE IF EXISTS "+ BankAccInfoHelper.TABLE_BANKACCINFO);
        db.execSQL(BankAccInfoHelper.CREATE_TABLE_BANKACCINFO);

        db.execSQL("DROP TABLE IF EXISTS "+ IncomeExpenseJournalHelper.TABLE_INCOMEEXPENSEJOURNAL);
        db.execSQL(IncomeExpenseJournalHelper.CREATE_TABLE_INCOMEEXPENSEJOURNAL);
    }
}
