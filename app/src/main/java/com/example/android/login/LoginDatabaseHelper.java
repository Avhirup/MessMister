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




    public final static String TABLE_MessMember = "MessMember";
    public final String MessMember_member_id= "_id";
    public final String MessMember_name = "name";
    public final String MessMember_start_date = "start_date";
    public final String MessMember_startof_month = "startof_month";
    public final String MessMember_is_active = "is_active";
    public final String MessMember_rate_id = "rate_id";
    public final String MessMember_due_amt = "due_amt";
    public final String MessMember_has_paid = "has_paid";
    public final String MessMember_is_late = "is_late";
    public final String MessMember_phone = "phone";
    public final String MessMember_img_id = "img_id";


    public final static String TABLE_Rate = "Rate";
    public final String Rate_rate_id = "_id";
    public final String Rate_category = "category";
    public final String Rate_amount = "amount";

    public final static String TABLE_Group = "MemberGroup";
    public final String Group_groupid = "_id";
    public final String Group_groupName = "group_name";

    public final static String TABLE_MessMember_Group="MessMemberGroup";
    public final String MessMember_Group_messmember_id="member_id";
    public final String MessMember_Group_group_id="group_id";

    public final static String TABLE_Notification = "Notification";
    public final String Notification_id = "_nid";
    public final String Notification_mid = "_mid";
    public final String Notification_notifyOn = "notifyOn";

    public final static String TABLE_Income= "Income";
    public final String Income_date="_date";
    public final String Income_tag="_tag";
    public final String Income_amount="_amount";

    public final static  String TABLE_Expense= "Expense ";
    public final String Expense_date="_date";
    public final String Expense_tag="_tag";
    public final String Expense_amount="_amount";

    public LoginDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //write your tables here - medha

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
                Group_groupid + " INTEGER primary key AUTOINCREMENT, " +
                Group_groupName + " TEXT  " +
                " ); ";
        db.execSQL(query3);


        String query2 = " create table " +  TABLE_MessMember +
                " ( " +
                MessMember_member_id + " INTEGER primary key AUTOINCREMENT, " +
                MessMember_name + " TEXT UNIQUE not NULL, " +
                MessMember_start_date + " TEXT, " +
                MessMember_startof_month + " TEXT, " +
                MessMember_is_active + " INTEGER, " +
                MessMember_rate_id + " INTEGER, " +
                MessMember_due_amt + " INTEGER, " +
                MessMember_has_paid + " BOOLEAN, " +
                MessMember_is_late + " INTEGER, " +
                MessMember_phone + " Text, " +
                MessMember_img_id + " INTEGER, "+
                " foreign key " + "( " +  MessMember_rate_id  + " )" + " references " + TABLE_Rate +
                " ); ";

        db.execSQL(query2);


        String query7=" create table "+TABLE_MessMember_Group+ " ( "+
                MessMember_Group_messmember_id +" INTEGER, "+
                MessMember_Group_group_id + " INTEGER, "+
                " foreign key " + "(" +  MessMember_Group_messmember_id  + ")" + " references " + TABLE_MessMember +"("+MessMember_member_id+")"+" , "+
                " foreign key " + "(" +  MessMember_Group_group_id  + ")" + " references " + TABLE_Group +"("+Group_groupid+")"+");";
        db.execSQL(query7);

        String query8 = " create table " + TABLE_Notification + " ( "+
                Notification_id + " INTEGER primary key AUTOINCREMENT, "+
                Notification_mid + " INTEGER, "+
                Notification_notifyOn + " TEXT, " +
                " foreign key " + "(" + Notification_mid  + ")" + " references " + TABLE_MessMember +"("+MessMember_member_id+")"+
                ");";
        db.execSQL(query8);

        String query9= "create table " + TABLE_Income + " ( "+
                Income_date + " TEXT , " +
                Income_tag  + " TEXT , " +
                Income_amount + "INTEGER );";

        db.execSQL(query9);

        String query4= "create table " + TABLE_Expense + " ( "+
                Expense_date + " TEXT , " +
                Expense_tag  + " TEXT , " +
                Expense_amount + "INTEGER );";

        db.execSQL(query4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

