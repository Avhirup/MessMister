package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by medha on 27/10/15.
 */
public class GroupDatabase extends LoginDatabaseHelper {

   
    SQLiteDatabase db;

    public GroupDatabase(Context context) {
        super(context,null,null,0);
        db = this.getWritableDatabase();
    }

    public  void add(Group group)
    {
        ContentValues values = new ContentValues();
        values.put(this.Group_groupName, group.getGroupName());
        db.insert(this.TABLE_Group,null,values);
    }

    public boolean delete(int id)
    {
        boolean check;
        try {
            String query = " delete from" + this.TABLE_Group + " where " + this.Group_groupid + " = " + id ;
            db.execSQL(query);
            check = true;
        }
        catch (Exception e){
            check = false;
        }
        return  check;
    }

    public boolean edit(Group group)
    {

        boolean check;
        try {
            String query = " update " + this.TABLE_Group +
                    " set " +
                    this.Group_groupName+ " = " + "\"" + group.getGroupName() + "\"" +

                    " where " + this.Group_groupid + " = " + group.getGroupID() + ";";
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
        String query="select "+MessMember_name+" from "+TABLE_MessMember_Group+" join "+TABLE_MessMember+" join "+TABLE_Group+
                " where "+TABLE_Group+"."+Group_groupName+" = "+group_name+";";

        Cursor cursor=db.rawQuery(query,null);
        return cursor;

    }
}
