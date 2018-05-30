package com.example.arup.personalaccount.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arup.personalaccount.Model.BankAccInformation;

import java.util.ArrayList;

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

    private String queryjoin = "SELECT T0.*,T1.bankName FROM BankAccInfo T0,BankInfo T1 WHERE T0.bankId=T1.bankId";

    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public BankAccInfoHelper(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public long insertBankAccInfo(BankAccInformation bankAccInformation){
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //contentValues.put(COL_HEADID,incomeExpenseHead.getHeadId());
            //contentValues.put(COL_ACCID,bankAccInformation.getAccId());
            contentValues.put(COL_BANKID,bankAccInformation.getBankId());
            contentValues.put(COL_ACCNAME,bankAccInformation.getAccName());
            contentValues.put(COL_BRANCHNAME,bankAccInformation.getBranchName());
            long _id = database.insert(TABLE_BANKACCINFO,null,contentValues);
            database.close();
            return _id;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return 0;
        }
    }

    public long updateBankAccInfo(BankAccInformation bankAccInformation){
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //contentValues.put(COL_HEADID,incomeExpenseHead.getHeadId());
            contentValues.put(COL_ACCID,bankAccInformation.getAccId());
            contentValues.put(COL_BANKID,bankAccInformation.getBankId());
            contentValues.put(COL_ACCNAME,bankAccInformation.getAccName());
            contentValues.put(COL_BRANCHNAME,bankAccInformation.getBranchName());
            long _id = database.update(TABLE_BANKACCINFO,contentValues,
                    ""+COL_ACCID+" =?",new String[]{String.valueOf(bankAccInformation.getAccId())});
            database.close();
            return _id;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return 0;
        }
    }

    public ArrayList<BankAccInformation> getBakAccList(){
        try{
            database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.rawQuery(queryjoin,null);
            ArrayList<BankAccInformation> bankAccInformationArrayList= new ArrayList<BankAccInformation>();
            if(cursor.moveToFirst()){
                do {
                    int bankAccId = cursor.getInt(cursor.getColumnIndex(COL_BANKID));
                    int bankId = cursor.getInt(cursor.getColumnIndex(COL_BANKID));
                    String bankName = cursor.getString(cursor.getColumnIndex("bankName"));
                    String accName = cursor.getString(cursor.getColumnIndex(COL_ACCNAME));
                    String branchName = cursor.getString(cursor.getColumnIndex(COL_BRANCHNAME));

                    BankAccInformation bankAccInformation = new BankAccInformation(bankAccId,bankId,accName,branchName,bankName);
                    bankAccInformationArrayList.add(bankAccInformation);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return bankAccInformationArrayList;

        }
        catch (Exception ex){
            throw ex;
           // return null;
        }
    }

    public BankAccInformation getBakAcc(int id){
        try{
            database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query(TABLE_BANKACCINFO,null,""+COL_ACCID+"=?",new String[]{Integer.toString(id)},null,null,null);
            BankAccInformation bankAccInformation=null;
            if(cursor.moveToFirst()){
                do {
                    int bankAccId = cursor.getInt(cursor.getColumnIndex(COL_BANKID));
                    int bankId = cursor.getInt(cursor.getColumnIndex(COL_BANKID));
                    String accName = cursor.getString(cursor.getColumnIndex(COL_ACCNAME));
                    String branchName = cursor.getString(cursor.getColumnIndex(COL_BRANCHNAME));

                    bankAccInformation = new BankAccInformation(bankAccId,bankId,accName,branchName);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return bankAccInformation;

        }
        catch (Exception ex){
            throw ex;
            // return null;
        }
    }
}
