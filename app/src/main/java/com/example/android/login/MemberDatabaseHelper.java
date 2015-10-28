package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by manjush on 28-10-2015.
 */
public class MemberDatabaseHelper {

    LoginDatabaseHelper helper;
    SQLiteDatabase db;

    public MemberDatabaseHelper(Context context)
    {

        helper = new LoginDatabaseHelper(context,null,null,0);
        db = helper.getWritableDatabase();

    }

    public void addMember(MessMember member)
    {
        ContentValues value = new ContentValues();
        value.put(helper.MessMember_name,member.getName());
        value.put(helper.MessMember_start_date,member.getStartDate());
        value.put(helper.MessMember_startof_month,member.getStartof_month());
        value.put(helper.MessMember_is_active,member.getIsActive());
        value.put(helper.MessMember_rate_id,member.getRate_id());
        value.put(helper.MessMember_due_amt,member.getDueAmount());
        value.put(helper.);

    }

}
