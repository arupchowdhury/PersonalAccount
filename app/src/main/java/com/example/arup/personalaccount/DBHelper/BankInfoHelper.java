package com.example.arup.personalaccount.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arup.personalaccount.Model.BankInformation;

import java.util.ArrayList;

public class BankInfoHelper {
    public static final String COL_BANKID="bankId";
    public static final String COL_BANKNAME= "bankName";

    public static final String TABLE_BANKINFO="BankInfo";

    public static final String CREATE_TABLE_BANKINFO=
            "CREATE TABLE "+TABLE_BANKINFO+"("+
            COL_BANKID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_BANKNAME+" TEXT"+
            ")";

    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public BankInfoHelper(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public long insertBankInfo(BankInformation bankInformation){
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //contentValues.put(COL_HEADID,incomeExpenseHead.getHeadId());
            contentValues.put(COL_BANKNAME,bankInformation.getBankName());

            long _id = database.insert(TABLE_BANKINFO,null,contentValues);
            database.close();
            return _id;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return 0;
        }
    }

    public long updateBankInfo(BankInformation bankInformation){
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_BANKID,bankInformation.getBankId());
            contentValues.put(COL_BANKNAME,bankInformation.getBankName());

            long _id = database.update(TABLE_BANKINFO,contentValues,
                    ""+COL_BANKID+" =?",new String[]{String.valueOf(bankInformation.getBankId())});
            database.close();
            return _id;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return 0;
        }
    }

    public long deleteBankInfo(int id){
        try{
            database = databaseHelper.getWritableDatabase();
            long _id = database.delete(TABLE_BANKINFO,
                    ""+COL_BANKID+"=?",new String[]{String.valueOf(id)});
            database.close();
            return _id;
        }
        catch (Exception ex){
            throw ex;
        }

    }

    public ArrayList<BankInformation> getBankList(){
        try{
            database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query(TABLE_BANKINFO,null,
                    null,null,null,null,null);
            ArrayList<BankInformation> bankInformationArrayList= new ArrayList<BankInformation>();

            if(cursor.moveToFirst()){
                do {
                    int bankId = cursor.getInt(cursor.getColumnIndex(COL_BANKID));
                    String bankName = cursor.getString(cursor.getColumnIndex(COL_BANKNAME));

                    BankInformation bankInformation = new BankInformation(bankId,bankName);
                    bankInformationArrayList.add(bankInformation);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return bankInformationArrayList;

        }
        catch (Exception ex){
            throw ex;
        }
    }

    public ArrayList<BankInformation> getcboBankList(){
        try{
            database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query(TABLE_BANKINFO,null,
                    null,null,null,null,null);
            ArrayList<BankInformation> bankInformationArrayList= new ArrayList<BankInformation>();
            if(cursor.moveToFirst()){
                do {
                    int bankId = cursor.getInt(cursor.getColumnIndex(COL_BANKID));
                    String bankName = cursor.getString(cursor.getColumnIndex(COL_BANKNAME));

                    BankInformation bankInformation = new BankInformation(bankId,bankName);
                    bankInformationArrayList.add(bankInformation);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return bankInformationArrayList;

        }
        catch (Exception ex){
            throw ex;
        }
    }


    public BankInformation getBankInfo(int id){
        try{
            database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query(TABLE_BANKINFO,null,COL_BANKID+"=?",new String[]{String.valueOf(id)},null,null,null);
            BankInformation bankInformation=null;
            if(cursor.moveToFirst()){
                do {
                    int bankId = cursor.getInt(cursor.getColumnIndex(COL_BANKID));
                    String bankName = cursor.getString(cursor.getColumnIndex(COL_BANKNAME));

                    bankInformation = new BankInformation(bankId,bankName);

                }while (cursor.moveToNext());

            }
            cursor.close();
            return bankInformation;
        }
        catch (Exception ex){
            throw ex;
        }

    }

    public BankInformation getBankInfoByName(String name){
        try{
            database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query(TABLE_BANKINFO,null,COL_BANKNAME+"=?",new String[]{String.valueOf(name)},null,null,null);
            BankInformation bankInformation=null;
            if(cursor.moveToFirst()){
                do {
                    int bankId = cursor.getInt(cursor.getColumnIndex(COL_BANKID));
                    String bankName = cursor.getString(cursor.getColumnIndex(COL_BANKNAME));

                    bankInformation = new BankInformation(bankId,bankName);

                }while (cursor.moveToNext());

            }
            cursor.close();
            return bankInformation;
        }
        catch (Exception ex){
            throw ex;
        }

    }
}
