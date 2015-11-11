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
public class GroupDatabase {

    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public GroupDatabase(Context context) {
        loginDatabaseHelper=new LoginDatabaseHelper(context,"LOGIN_DB",null,1);
        //super(context,null,null,0);
        db = loginDatabaseHelper.getWritableDatabase();
    }

    public  void add(MemberGroup group)
    {
        Log.e("helper", "In add");
        ContentValues values = new ContentValues();
        values.put(loginDatabaseHelper.Group_groupName, group.getGroupName());
        db.insert(loginDatabaseHelper.TABLE_Group, null, values);
    }

    public boolean delete(int id)
    {
        boolean check;
        try {
            String query = " delete from" + loginDatabaseHelper.TABLE_Group + " where " + loginDatabaseHelper.Group_groupid + " = " + id ;
            db.execSQL(query);
            check = true;
        }
        catch (Exception e){
            check = false;
        }
        return  check;
    }

    public boolean edit(MemberGroup group)
    {

        boolean check;
        try {
            String query = " update " + loginDatabaseHelper.TABLE_Group +
                    " set " +
                    loginDatabaseHelper.Group_groupName+ " = " + "\"" + group.getGroupName() + "\"" +

                    " where " + loginDatabaseHelper.Group_groupid + " = " + group.getGroupID() + ";";
            db.execSQL(query);
            check = true;
        }
        catch (Exception e)
        {
            check = false;
        }
        return  check;
    }

    public Cursor findMembers(String group_name)
    {
        String query="select "+loginDatabaseHelper.MessMember_name+" from "+loginDatabaseHelper.TABLE_MessMember_Group+" join "+loginDatabaseHelper.TABLE_MessMember+" join "
                +loginDatabaseHelper.TABLE_Group+
                " where "+loginDatabaseHelper.TABLE_Group+"."+loginDatabaseHelper.Group_groupName+" = "+group_name+";";

        Cursor cursor=db.rawQuery(query,null);
        return cursor;

    }

    public Cursor getGroupTable()
    {
        String query = "select * from " + loginDatabaseHelper.TABLE_Group +";";
        Cursor cursor = db.rawQuery(query,null);
        return  cursor;
    }

    public ArrayList<String> getGroupNames()
    {
        ArrayList<String> grouplist = new ArrayList<String>();
        String query ="(select * from " + loginDatabaseHelper.TABLE_Group + ";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor==null)
            Log.e("he","in array cursor null");
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {

            grouplist.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return grouplist;
    }

}
