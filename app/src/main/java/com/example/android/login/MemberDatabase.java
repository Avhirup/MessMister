package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by medha on 27/10/15.
 */
public class MemberDatabase extends LoginDatabaseHelper {

   
    SQLiteDatabase db;

    public MemberDatabase(Context context)
    {

       super(context,null,null,0);
        db = this.getWritableDatabase();

    }

    public void add(MessMember member) {
        ContentValues value = new ContentValues();
        value.put(this.MessMember_name,member.getName());
        value.put(this.MessMember_start_date,member.getStart_date());
        value.put(this.MessMember_startof_month,member.getStartof_month());
        value.put(this.MessMember_is_active,member.getIs_active());
        value.put(this.MessMember_rate_id,member.getRate_id());
        value.put(this.MessMember_due_amt,member.getDue_amount());
        value.put(this.MessMember_has_paid,member.getHas_paid());
        value.put(this.MessMember_is_late,member.getIs_late());
        value.put(this.MessMember_phone,member.getPhone());

        db.insert(this.TABLE_MessMember,null,value);

    }

   /* public int getMemberId(MessMember member)
    {
        String query = " select " +this.MessMember_member_id + " from " +  this.TABLE_MessMember + " where " +
                this.MessMember_name + " = " + "\"" + member.getName() + "\"" +
                this.MessMember_start_date + " = " + "\"" + member.getStart_date() + "\"" +
                this.MessMember_startof_month + " = " + "\"" + member.getStartof_month()  + "\"" +
                this.MessMember_is_active + " = " + "\"" + member.getIs_active()  + "\"" +
                this.MessMember_rate_id  + " = " + "\"" + member.getRate_id()  + "\"" +
                this.MessMember_due_amt  + " = " + "\"" + member.getDue_amount()  + "\"" +
                this.MessMember_has_paid + " = " + "\"" + member.getHas_paid() + "\"" +
                this.MessMember_is_late + " = " + "\"" + member.getIs_late() + "\"" +
                this.MessMember_phone + " = " + "\"" + member.getPhone() + "\"";

        Cursor c = db.rawQuery(query,null);
        int a = 0;
        try {
            if(c==null)
                //Log.e("hello", "welcome");
                c.moveToFirst();
        }
        catch (Exception e){}

        while (!c.isAfterLast())
        {

                a = c.getInt(0);

            c.moveToNext();
        }
        return a;
    }*/

    public Boolean delete(Integer id)
    {
        boolean check;
        try {
            String query = " delete from" + this.TABLE_MessMember + " where " + this.MessMember_member_id + " = " + id ;
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
            String query = " update  " + this.TABLE_MessMember +
                    " set " +
                    this.MessMember_name + " = " + "\"" + member.getName() + "\", " +
                    this.MessMember_start_date + " = " +  member.getStart_date() + ", " +
                    this.MessMember_startof_month + " = " + member.getStartof_month() + ", " +
                    this.MessMember_is_active + " = " +  member.getIs_active() + ", " +
                    this.MessMember_rate_id + " = " +  member.getRate_id() + ", " +
                    this.MessMember_due_amt + " = " +  member.getDue_amount() + ", " +
                    this.MessMember_has_paid + " = " +  member.getHas_paid() + ", " +
                    this.MessMember_is_late + " = " + member.getIs_late() + ", " +
                    this.MessMember_phone + " = " + "\"" + member.getPhone() + "\" " +
                    " where " + this.MessMember_member_id + " = " + member.getMember_id() + ";";

                   db.execSQL(query);
                    check = true;

        }
        catch (Exception e ){
            check = false;
        }
        return  check;
    }

}
