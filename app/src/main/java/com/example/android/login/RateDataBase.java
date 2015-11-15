package com.example.android.login;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by medha on 27/10/15.
 */
public class RateDataBase {

    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public RateDataBase(Context context){
        loginDatabaseHelper=new LoginDatabaseHelper(context,"LOGIN_DB",null,1);
        db = loginDatabaseHelper.getWritableDatabase();
    }


    public void add(Rate rate) {
        Log.e("helper", "in add");
        ContentValues values = new ContentValues();
        values.put(loginDatabaseHelper.Rate_category,rate.getCategory());
        values.put(loginDatabaseHelper.Rate_amount,rate.getAmount());

        db.insert(loginDatabaseHelper.TABLE_Rate,null,values);

    }


    public boolean delete(Integer rateId) {
        boolean check;
        try {
            String query = " delete from" + loginDatabaseHelper.TABLE_Rate + " where " + loginDatabaseHelper.Rate_rate_id+ " = " + rateId ;
            db.execSQL(query);
            check = true;
        }
        catch (Exception e){
            check = false;
        }
        return  check;
    }


    public Boolean edit(Rate rate) {
        boolean check;
        try {
            String query = " update " + loginDatabaseHelper.TABLE_Rate +
                    " set " +
                    loginDatabaseHelper.Rate_category + " = " + "\"" + rate.getCategory() + "\"," +
                    loginDatabaseHelper.Rate_amount + " = " +  rate.getAmount()  +
                    " where " + loginDatabaseHelper.Rate_rate_id + " = " + rate.getRate_id() + ";";
            db.execSQL(query);
            check = true;
        }
        catch (Exception e)
        {
            check = false;
        }
        return  check;
    }

    public Cursor getAmount(String category)
    {
        String query="select "+loginDatabaseHelper.Rate_amount+" from "+loginDatabaseHelper.TABLE_Rate+
                " where "+loginDatabaseHelper.TABLE_Rate+"."+loginDatabaseHelper.Rate_category+" = "+category+";";

        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }

    public Cursor getRateTable()
    {
        String query="select * from "+loginDatabaseHelper.TABLE_Rate+" ;";
        Cursor cursor=db.rawQuery(query,null);
        return  cursor;
    }

}
