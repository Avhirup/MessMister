package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by avhirup on 11/16/2015.
 */
public class IncomeDatabase {

    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public IncomeDatabase(Context context)
    {

        loginDatabaseHelper=new LoginDatabaseHelper(context,"LOGIN_DB",null,1);
        db = loginDatabaseHelper.getWritableDatabase();

    }

    public void add(Income income) {
        ContentValues value = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());
        value.put(loginDatabaseHelper.Income_date,date);
        value.put(loginDatabaseHelper.Income_tag,income.getIncomeName());
        value.put(loginDatabaseHelper.Income_amount,income.getAmount());

        db.insert(loginDatabaseHelper.TABLE_Income,null,value);

    }

}
