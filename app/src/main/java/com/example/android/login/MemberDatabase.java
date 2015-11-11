package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by medha on 27/10/15.
 */
public class MemberDatabase  {

    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public MemberDatabase(Context context)
    {

        loginDatabaseHelper=new LoginDatabaseHelper(context,"LOGIN_DB",null,1);
        db = loginDatabaseHelper.getWritableDatabase();

    }

    public void add(MessMember member) {
        ContentValues value = new ContentValues();
        value.put(loginDatabaseHelper.MessMember_name,member.getName());
        value.put(loginDatabaseHelper.MessMember_start_date,member.getStart_date());
        value.put(loginDatabaseHelper.MessMember_startof_month,member.getStartof_month());
        value.put(loginDatabaseHelper.MessMember_is_active,member.getIs_active());
        value.put(loginDatabaseHelper.MessMember_rate_id,member.getRate_id());
        value.put(loginDatabaseHelper.MessMember_due_amt,member.getDue_amount());
        value.put(loginDatabaseHelper.MessMember_has_paid,member.getHas_paid());
        value.put(loginDatabaseHelper.MessMember_is_late,member.getIs_late());
        value.put(loginDatabaseHelper.MessMember_phone,member.getPhone());
        value.put(loginDatabaseHelper.MessMember_img_id, member.getImg_id());

        db.insert(loginDatabaseHelper.TABLE_MessMember,null,value);

    }

    public int getMemberId(MessMember member)
    {
        String query = " select " +loginDatabaseHelper.MessMember_member_id + " from " +  loginDatabaseHelper.TABLE_MessMember +
                " where " + loginDatabaseHelper.MessMember_name + " = " + "\"" + member.getName() + "\";" ;

        Cursor cursor = db.rawQuery(query,null);
        int a =0;
        if(cursor==null)
            Log.e("he","in array cursor null");
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {

            a=cursor.getInt(0);

            //Log.e("Member table",tuple);
            cursor.moveToNext();
        }
        return a;
    }

    public Boolean delete(Integer id)
    {
        boolean check;
        try {
            String query = " delete from" + loginDatabaseHelper.TABLE_MessMember + " where " + loginDatabaseHelper.MessMember_member_id + " = " + id ;
            db.execSQL(query);
            check = true;
        }
        catch (Exception e){
            check = false;
        }
        return  check;
    }


    public Boolean edit(MessMember member) {
        boolean check;
        try {
            String query = " update  " + loginDatabaseHelper.TABLE_MessMember +
                    " set " +
                    loginDatabaseHelper.MessMember_name + " = " + "\"" + member.getName() + "\", " +
                    loginDatabaseHelper.MessMember_start_date + " = " +  member.getStart_date() + ", " +
                    loginDatabaseHelper.MessMember_startof_month + " = " + member.getStartof_month() + ", " +
                    loginDatabaseHelper.MessMember_is_active + " = " +  member.getIs_active() + ", " +
                    loginDatabaseHelper.MessMember_rate_id + " = " +  member.getRate_id() + ", " +
                    loginDatabaseHelper.MessMember_due_amt + " = " +  member.getDue_amount() + ", " +
                    loginDatabaseHelper.MessMember_has_paid + " = " +  member.getHas_paid() + ", " +
                    loginDatabaseHelper.MessMember_is_late + " = " + member.getIs_late() + ", " +
                    loginDatabaseHelper.MessMember_phone + " = " + "\"" + member.getPhone() + "\" " +
                    loginDatabaseHelper.MessMember_img_id + " = " + "\"" + member.getImg_id() + "\" " +
                    " where " + loginDatabaseHelper.MessMember_member_id + " = " + member.getMember_id() + ";";

                   db.execSQL(query);
                    check = true;

        }
        catch (Exception e ){
            check = false;
        }
        return  check;
    }

    public Cursor getMemberTable()
    {
        String query = "select * from " + loginDatabaseHelper.TABLE_MessMember +";";
        Cursor cursor = db.rawQuery(query,null);
        return  cursor;
    }

    public ArrayList<String> getAllMembers()
    {
        ArrayList<String> memberlist = new ArrayList<String>();
        String query = "select * from " + loginDatabaseHelper.TABLE_MessMember +";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor==null)
            Log.e("he","in array cursor null");
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {

            memberlist.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return memberlist;
    }

    public ArrayList<String> getlateMembers()
    {
        ArrayList<String> memberlist = new ArrayList<String>();
        String query = "select * from " + loginDatabaseHelper.TABLE_MessMember +
                " where " +loginDatabaseHelper.MessMember_is_late + " = 1"+";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor==null)
            Log.e("he","in array cursor null");
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {

            memberlist.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return memberlist;
    }

    public ArrayList<String> getDueMembers()
    {
        ArrayList<String> memberlist = new ArrayList<String>();
        String query ="(select * from " + loginDatabaseHelper.TABLE_MessMember + ")"+
                " NOT IN "+ "(select * from " + loginDatabaseHelper.TABLE_MessMember +
                " where " +loginDatabaseHelper.MessMember_due_amt + " = 0)"+";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor==null)
            Log.e("he","in array cursor null");
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {

            memberlist.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return memberlist;
    }

    public int getrate_id(int _id)
    {
        String query = "select * from " + loginDatabaseHelper.TABLE_MessMember +
                " where "+ loginDatabaseHelper.MessMember_member_id+"="+_id+";";
        Cursor cursor = db.rawQuery(query,null);


        int a =0;
        if(cursor==null)
            Log.e("he","in array cursor null");
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {

            a=cursor.getInt(5);

            //Log.e("Member table",tuple);
            cursor.moveToNext();
        }
        return a;

    }

    public void setDueamt(int mid,int amt)
    {

        String query =  " update  " + loginDatabaseHelper.TABLE_MessMember +
                " set " +
                loginDatabaseHelper.MessMember_due_amt + " = " + amt  +
                " where " + loginDatabaseHelper.MessMember_member_id + " = " + mid + ";";

        db.execSQL(query);
    }
}
