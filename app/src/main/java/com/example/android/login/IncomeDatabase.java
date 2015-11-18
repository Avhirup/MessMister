package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        String query="insert into "+LoginDatabaseHelper.TABLE_Income+
                " values ( \""+ date +"\" , \""+income.getIncomeName()+"\" , "+income.getAmount()+" ) ; ";
        Log.e("helper", query);
        db.execSQL(query);

    }

    public Cursor getIncome()
    {
        String query="select * from "+LoginDatabaseHelper.TABLE_Income+" ;";
        return db.rawQuery(query,null);
    }
/*
    public Cursor getMonth()
    {

    }

    public Cursor getYear()
    {

    }

    public Cursor groupByTag(String tag,int year)
    {

    }

    public Cursor groupByTag(String tag,int month)
    {

    }
    public Cursor groupByMonth(int month)
    {

    }

    public Cursor groupByToday()
    {

    }

    public Cursor groupByYear(int year)
    {

    }





*/
}
