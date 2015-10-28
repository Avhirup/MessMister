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


    public final String TABLE_MessMember = "MessMember";
    public final String MessMember_member_id= "member_id";
    public final String MessMember_name = "name";
    public final String MessMember_start_date = "start_date";
    public final String MessMember_startof_month = "startof_month";
    public final String MessMember_is_active = "is_active";
    public final String MessMember_rate_id = "rate_id";
    public final String MessMember_due_amt = "due_amt";
    public final String MessMember_has_paid = "has_paid";
    public final String MessMember_is_late = "is_late";
    public final String MessMember_phone = "phone";


    public final String TABLE_Rate = "Rate";
    public final String Rate_rate_id = "rate_id";
    public final String Rate_category = "category";
    public final String Rate_amount = "amount";

    public final String TABLE_Group = "Group";
    public final String Group_groupid = "group_id";
    public final String Group_groupName = "group_name";



    public LoginDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //write your tables here - medha

        Log.e("helper", "onCreate called");
        db.execSQL("create table login(_id integer primary key autoincrement, " +
                "name text, password text);");


        String query1 = " create table " + TABLE_Rate +
                " ( " +
                Rate_rate_id + " INTEGER primary key AUTOINCREMENT, " +
                Rate_category + " TEXT, " +
                Rate_amount + " INTEGER " +
                " ); ";

        db.execSQL(query1);

        String query3 = " create table " + TABLE_Group +
                " ( " +
                Group_groupid + " INTEGER primary key AUTOINCREMENT, "+
                Group_groupName + " TEXT  " +
                " ); ";
        db.execSQL(query3);

        String query2 = " create table " +  TABLE_MessMember +
                " ( " +
                MessMember_member_id + " INTEGER primary key AUTOINCREMENT, " +
                MessMember_name + " TEXT not NULL, " +
                MessMember_start_date + " INTEGER, " +
                MessMember_startof_month + " INTEGER, " +
                MessMember_is_active + " INTEGER, " +
                MessMember_rate_id + " INTEGER, " +
                MessMember_due_amt + " INTEGER, " +
                MessMember_has_paid + " INTEGER, " +
                MessMember_is_late + " INTEGER, " +
                MessMember_phone + " Text, " +
                " foreign key " + "(" +  MessMember_rate_id  + ")" + " references " + TABLE_Rate +
                " ); ";

        db.execSQL(query2);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

