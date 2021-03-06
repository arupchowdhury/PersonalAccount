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



    DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    BankInfoHelper bankInfoHelper;

    public BankAccInfoHelper(Context context) {

        this.databaseHelper = new DatabaseHelper(context);
        bankInfoHelper = new BankInfoHelper(context);
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

    public long deleteContactInfo(int id){
        try{
            database = databaseHelper.getWritableDatabase();
            long _id = database.delete(TABLE_BANKACCINFO,
                    ""+COL_ACCID+"=?",new String[]{String.valueOf(id)});
            database.close();
            return _id;
        }
        catch (Exception ex){
            throw ex;
        }

    }

    public ArrayList<BankAccInformation> getBakAccList(){
        try{
            String queryjoin = "SELECT T0.*,T1.bankName FROM "+TABLE_BANKACCINFO+" T0 INNER JOIN "+bankInfoHelper.TABLE_BANKINFO+" T1 ON T0."+COL_BANKID+"=T1."+bankInfoHelper.COL_BANKID;
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
            database.close();
            return bankAccInformationArrayList;

        }
        catch (Exception ex){
            throw ex;
           // return null;
        }
    }

    public ArrayList<BankAccInformation> getBakAccListByBankId(int bankid){
        try{
            database = databaseHelper.getReadableDatabase();
            String query = "SELECT "+COL_ACCID+","+COL_ACCNAME+" FROM "+TABLE_BANKACCINFO+""+" WHERE "+COL_BANKID+"="+Integer.toString(bankid);
            Cursor cursor = database.rawQuery(query,null);
            ArrayList<BankAccInformation> bankAccInformationArrayList= new ArrayList<BankAccInformation>();
            bankAccInformationArrayList.add(new BankAccInformation(0,"--Select Accunt--"));
            if(cursor.moveToFirst()){
                do {
                    int bankAccId = cursor.getInt(cursor.getColumnIndex(COL_ACCID));
//                    int bankId = cursor.getInt(cursor.getColumnIndex(COL_BANKID));
//                    String bankName = cursor.getString(cursor.getColumnIndex("bankName"));
                    String accName = cursor.getString(cursor.getColumnIndex(COL_ACCNAME));
//                    String branchName = cursor.getString(cursor.getColumnIndex(COL_BRANCHNAME));

                    BankAccInformation bankAccInformation = new BankAccInformation(bankAccId,accName);
                    bankAccInformationArrayList.add(bankAccInformation);
                }while (cursor.moveToNext());
            }
            cursor.close();
            database.close();
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
            database.close();
            return bankAccInformation;

        }
        catch (Exception ex){
            throw ex;
            // return null;
        }
    }
}
