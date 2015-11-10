package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by manjush on 10-11-2015.
 */
public class MessMemberGroupDatabase {

    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public MessMemberGroupDatabase(Context context)
    {

        loginDatabaseHelper=new LoginDatabaseHelper(context,"LOGIN_DB",null,1);
        db = loginDatabaseHelper.getWritableDatabase();

    }

    public  void add(MessMemberGroup m)
    {
        ContentValues value = new ContentValues();
        value.put(loginDatabaseHelper.MessMember_Group_messmember_id,m.getMid());
        value.put(loginDatabaseHelper.MessMember_Group_group_id,m.getGid());
        db.insert(loginDatabaseHelper.TABLE_MessMember_Group,null,value);

    }
}
