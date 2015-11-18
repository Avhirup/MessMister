package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

        String query="insert into "+LoginDatabaseHelper.TABLE_Expense+
                " values ( \""+ date +"\" , \""+expense.getExpName()+"\" , "+expense.getAmount()+" ) ; ";
        Log.e("helper",query);
        db.execSQL(query);

    }

    public Cursor getExpense()
    {
        String query="select * from "+LoginDatabaseHelper.TABLE_Expense+" ;";
        return db.rawQuery(query,null);
    }
}
