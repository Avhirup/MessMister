package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by medha on 10/9/15.
 */
public class LoginDatabaseHelper extends SQLiteOpenHelper {


    public LoginDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //write your tables here - medha

        Log.e("helper", "onCreate called");
        db.execSQL("create table login(_id integer primary key autoincrement, " +
                "name text, password text);");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

