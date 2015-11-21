package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by avhirup on 11/16/2015.
 */
public class ExpenseDatabase {
    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public ExpenseDatabase(Context context)
    {

        loginDatabaseHelper=new LoginDatabaseHelper(context,"LOGIN_DB",null,1);
        db = loginDatabaseHelper.getWritableDatabase();

    }

    public void add(Expense expense) {
        ContentValues value = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());
        value.put(loginDatabaseHelper.Expense_date,date);
        value.put(loginDatabaseHelper.Expense_tag,expense.getExpName());
        value.put(loginDatabaseHelper.Expense_amount,expense.getAmount());

        db.insert(loginDatabaseHelper.TABLE_Income,null,value);

    }
}
