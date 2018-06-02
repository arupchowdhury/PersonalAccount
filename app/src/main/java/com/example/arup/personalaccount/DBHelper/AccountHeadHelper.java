package com.example.arup.personalaccount.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arup.personalaccount.Model.IncomeExpenseHead;

import java.util.ArrayList;

public class AccountHeadHelper {
    public static final String COL_HEADID= "headid";
    public static final String COL_HEADNAME =  "headname";
    public static final String COL_HEADTYPE = "headtype";

    public static final String TABLE_ACCOUNTHEAD="accounthead";

    public static final String CREATE_TABLE_ACCOUNTHEAD="" +
            "CREATE TABLE "+TABLE_ACCOUNTHEAD +"("+
            COL_HEADID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_HEADNAME+" TEXT,"+
            COL_HEADTYPE+" INTEGER"+
            ")"
            ;


    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public AccountHeadHelper(Context context) {

        databaseHelper = new DatabaseHelper(context);
    }

    public long insertAccountHead(IncomeExpenseHead incomeExpenseHead){
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //contentValues.put(COL_HEADID,incomeExpenseHead.getHeadId());
            contentValues.put(COL_HEADNAME,incomeExpenseHead.getHeadName());
            contentValues.put(COL_HEADTYPE,incomeExpenseHead.getHeadType());
            long _id = database.insert(TABLE_ACCOUNTHEAD,null,contentValues);
            database.close();
            return _id;
        }
        catch (Exception ex){
            ex.getStackTrace();
            return 0;
        }
    }

    public long updateAccountHead(IncomeExpenseHead incomeExpenseHead){
        try{
            database = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_HEADID,incomeExpenseHead.getHeadId());
            contentValues.put(COL_HEADNAME,incomeExpenseHead.getHeadName());
            contentValues.put(COL_HEADTYPE,incomeExpenseHead.getHeadType());
            long _id = database.update(TABLE_ACCOUNTHEAD,contentValues,
                    ""+COL_HEADID+" =?",new String[]{String.valueOf(incomeExpenseHead.getHeadId())});
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
            long _id = database.delete(TABLE_ACCOUNTHEAD,
                    ""+COL_HEADID+"=?",new String[]{String.valueOf(id)});
            database.close();
            return _id;
        }
        catch (Exception ex){
            throw ex;
        }

    }


    public ArrayList<IncomeExpenseHead> getIncomeExpenseHeadList(){
        try{
            database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query(TABLE_ACCOUNTHEAD,null,
                    null,null,null,null,null);
            ArrayList<IncomeExpenseHead> incomeExpenseHeadArrayList= new ArrayList<IncomeExpenseHead>();

            if(cursor.moveToFirst()){
                do {
                    int accHeadId = cursor.getInt(cursor.getColumnIndex(COL_HEADID));
                    String accHeadName = cursor.getString(cursor.getColumnIndex(COL_HEADNAME));
                    String accHeadType = cursor.getString(cursor.getColumnIndex(COL_HEADTYPE));
                    IncomeExpenseHead incomeExpenseHead = new IncomeExpenseHead(accHeadId,accHeadName,accHeadType);
                    incomeExpenseHeadArrayList.add(incomeExpenseHead);
                }while (cursor.moveToNext());
            }
            cursor.close();
            database.close();
            return incomeExpenseHeadArrayList;

        }
        catch (Exception ex){
            throw ex;
        }
    }

    public ArrayList<IncomeExpenseHead> getcboIncomeExpenseHeadList(String type){
        try{
            String queryjoin = "SELECT "+COL_HEADID+","+COL_HEADNAME+" FROM accounthead  WHERE "+COL_HEADTYPE+"='"+type+"'";
            database = databaseHelper.getReadableDatabase();
//            Cursor cursor = database.query(TABLE_ACCOUNTHEAD,null,
//                    COL_HEADID+"=?",new String[]{type},null,null,null);
            Cursor cursor = database.rawQuery(queryjoin,null);
            ArrayList<IncomeExpenseHead> incomeExpenseHeadArrayList= new ArrayList<IncomeExpenseHead>();
            incomeExpenseHeadArrayList.add(new IncomeExpenseHead(0,"--Select "+type+"--"));
            if(cursor.moveToFirst()){
                do {
                    int accHeadId = cursor.getInt(cursor.getColumnIndex(COL_HEADID));
                    String accHeadName = cursor.getString(cursor.getColumnIndex(COL_HEADNAME));
                    //String accHeadType = cursor.getString(cursor.getColumnIndex(COL_HEADTYPE));
                    IncomeExpenseHead incomeExpenseHead = new IncomeExpenseHead(accHeadId,accHeadName);
                    incomeExpenseHeadArrayList.add(incomeExpenseHead);
                }while (cursor.moveToNext());
            }
            cursor.close();
            database.close();
            return incomeExpenseHeadArrayList;

        }
        catch (Exception ex){
            throw ex;
        }
    }

    public IncomeExpenseHead getIncomeExpenseHead(int id){
        try{
            database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query(TABLE_ACCOUNTHEAD,null,COL_HEADID+"=?",new String[]{String.valueOf(id)},null,null,null);
            IncomeExpenseHead incomeExpenseHead=new IncomeExpenseHead();
            if(cursor.moveToFirst()){
                do {
                    int accHeadId = cursor.getInt(cursor.getColumnIndex(COL_HEADID));
                    String accHeadName = cursor.getString(cursor.getColumnIndex(COL_HEADNAME));
                    String accHeadType = cursor.getString(cursor.getColumnIndex(COL_HEADTYPE));
                    incomeExpenseHead = new IncomeExpenseHead(accHeadId,accHeadName,accHeadType);

                }while (cursor.moveToNext());

            }
            cursor.close();
            database.close();
            return incomeExpenseHead;
        }
        catch (Exception ex){
            throw ex;
        }

    }

    public IncomeExpenseHead getIncomeExpenseHeadByName(String name){
        try{
            database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query(TABLE_ACCOUNTHEAD,null,COL_HEADNAME+"=?",new String[]{String.valueOf(name)},null,null,null);
            IncomeExpenseHead incomeExpenseHead=null;
            if(cursor.moveToFirst()){
                do {
                    int accHeadId = cursor.getInt(cursor.getColumnIndex(COL_HEADID));
                    String accHeadName = cursor.getString(cursor.getColumnIndex(COL_HEADNAME));
                    String accHeadType = cursor.getString(cursor.getColumnIndex(COL_HEADTYPE));
                    incomeExpenseHead = new IncomeExpenseHead(accHeadId,accHeadName,accHeadType);

                }while (cursor.moveToNext());

            }
            cursor.close();
            database.close();
            return incomeExpenseHead;
        }
        catch (Exception ex){
            throw ex;
        }

    }

    //new String[]{COL_HEADID,COL_HEADNAME}
}
