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

    public final static String TABLE_Group = "MemberGroup";
    public final String Group_groupid = "group_id";
    public final String Group_groupName = "group_name";


    public final static String TABLE_Expense= "Expense";
    public final String Expense_expense_id= "expense_id";
    public final String Expense_expense_name ="expense_name";
    public final String Expense_amount= "amount";

    public final static String TABLE_Income= "Income";
    public final String Income_income_id="income_id";
    public final String Income_month="month";
    public final String Income_year="year";
    public final String Income_amountlost="amountlost";
    public final String Income_amountearned="amountearned";

    public final static String TABLE_Expense_Income ="ExpenseIncome";
    public final String Expense_Income_expense_id= "expense_id";
    public final String Expense_Income_income_id= "income_id";

    public final static String TABLE_MessMember_Group="MessMemberGroup";
    public final String MessMember_Group_messmember_id="member_id";
    public final String MessMember_Group_group_id="group_id";

    public LoginDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //write your tables here - medha

        Log.e("helper", "onCreate called");
        db.execSQL("create table login(_id integer primary key autoincrement, " +
                "name text, password text);");

        Log.e("helper", "Create rate called");
        String query1 = " create table " + TABLE_Rate +
                " ( " +
                Rate_rate_id + " INTEGER primary key AUTOINCREMENT, " +
                Rate_category + " TEXT, " +
                Rate_amount + " INTEGER " +
                " ); ";

        db.execSQL(query1);
      Log.e("helper", "Create group called");
        String query3 = " create table " + TABLE_Group +
                " ( " +
                Group_groupid + " INTEGER primary key AUTOINCREMENT, " +
                Group_groupName + " TEXT  " +
                " ); ";
        db.execSQL(query3);
        Log.e("helper", "Create member called");
        //db.execSQL("create table Group ( group_id integer primary key AUTOINCREMENT, group_name text);");
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
                " foreign key " + "( " +  MessMember_rate_id  + " )" + " references " + TABLE_Rate +
                " ); ";

        db.execSQL(query2);

        String query5="create table "+ TABLE_Expense +" ( "+
                Expense_expense_id + " INTEGER primary key AUTOINCREMENT, "+
                Expense_expense_name + " TEXT not null, " +
                Expense_amount + "INTEGER "+
                ");";

        db.execSQL(query5);


        String query4=" create table "+ TABLE_Income +" ( "+
                Income_income_id + " INTEGER primary key AUTOINCREMENT, "+
                Income_month + " INTEGER, "+
                Income_year + " INTEGER, "+
                Income_amountearned +" INTEGER, "+
                Income_amountlost +" INTEGER "+
                " );";

        db.execSQL(query4);


        String query6=" create table "+ TABLE_Expense_Income+ " ( "+
                Expense_Income_expense_id + " INTEGER, "+
                Expense_Income_income_id + " INTEGER, "+
                " foreign key " + "(" +  Expense_expense_id  + ")" + " references " + TABLE_Expense +" , "+
                " foreign key " + "(" +  Expense_Income_income_id  + ")" + " references " + TABLE_Income + " );";

        db.execSQL(query6);

        /*
        String query7=" create table "+TABLE_MessMember_Group+ " ( "+
                MessMember_Group_messmember_id +" INTEGER, "+
                MessMember_Group_group_id + "INTEGER, "+
                " foreign key " + "(" +  MessMember_Group_messmember_id  + ")" + " references " + TABLE_MessMember +" , "+
                " foreign key " + "(" +  MessMember_Group_group_id  + ")" + " references " + TABLE_Group + " );";
        db.execSQL(query7);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

