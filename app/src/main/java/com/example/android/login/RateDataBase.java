package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by medha on 27/10/15.
 */
public class RateDataBase extends LoginDatabaseHelper {


    SQLiteDatabase db;
    public RateDataBase(Context context) {
        super(context,null,null,0);
        db = this.getWritableDatabase();
    }


    public void add(Rate rate) {
        ContentValues values = new ContentValues();
        values.put(this.Rate_category,rate.getCategory());
        values.put(this.Rate_amount,rate.getAmount());

        db.insert(this.TABLE_Rate,null,values);

    }


    public boolean delete(Integer rateId) {
        boolean check;
        try {
            String query = " delete from" + this.TABLE_Rate + " where " + this.Rate_rate_id+ " = " + rateId ;
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
            String query = " update " + this.TABLE_Rate +
                    " set " +
                    this.Rate_category + " = " + "\"" + rate.getCategory() + "\"," +
                    this.Rate_amount + " = " +  rate.getAmount()  +
                    " where " + this.Rate_rate_id + " = " + rate.getRateid() + ";";
            db.execSQL(query);
            check = true;
        }
        catch (Exception e)
        {
            check = false;
        }
        return  check;
    }



}
