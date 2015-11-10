package com.example.android.login;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateMember extends AppCompatActivity {

    Toolbar toolbar;
    public int year, day, month;
    LoginDatabaseHelper loginDatabaseHelper;
    static SQLiteDatabase sqLiteDatabase=null;

    MemberDatabase memberDatabase;
    MessMemberGroupDatabase memberGroupDatabase;
    AutoCompleteTextView name;
    AutoCompleteTextView phone;
    Spinner dayspin;
    MessMember m;
    MessMemberGroup memberGroup;
    public static ArrayList<Integer> selectedItms;
    Button rate;
    public static int rate_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_member);
        toolbar = (Toolbar)findViewById(R.id.t_bar);
        setSupportActionBar(toolbar);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        displayDate();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
       // setRateSpinner();

        loginDatabaseHelper =new LoginDatabaseHelper(this,"LOGIN_DB",null,1);
        sqLiteDatabase=loginDatabaseHelper.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_member, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id == R.id.action_done) {
           // Log.e("kk","jj");
            memberDatabase = new MemberDatabase(this);
            memberGroupDatabase = new MessMemberGroupDatabase(this);
            name = (AutoCompleteTextView)findViewById(R.id.name);
            phone = (AutoCompleteTextView)findViewById(R.id.phone);
            dayspin = (Spinner)findViewById(R.id.dayspin);


            String mname = name.getText().toString();
            String mphone = phone.getText().toString();

            int day = dayspin.getSelectedItemPosition()+1;
            String start_date = Integer.toString(day)+ "-" + Integer.toString(month) +"-"+Integer.toString(year);


            m = new MessMember();
            m.setName(mname);
            m.setStart_date(start_date);
            m.setStartof_month(start_date);
            m.setDue_amount(0);
            m.setHas_paid(true);
            m.setIs_active(true);
            m.setIs_late(false);
            m.setPhone(mphone);
            m.setRate_id(rate_id);

            memberDatabase.add(m);
            int mid = memberDatabase.getMemberId(m);
            Log.d("getid", Integer.toString(mid));
            int a;
            for(int i=0 ; i<selectedItms.size();i++)
            {
                try{
                    a = selectedItms.get(i).intValue()+1;
                    Log.d("in loop", Integer.toString(a));
                    memberGroup = new MessMemberGroup(mid,a);
                    memberGroupDatabase.add(memberGroup);
                }
                catch(Exception e){}

                /*String query = " insert into " + loginDatabaseHelper.TABLE_MessMember_Group + " values("+ Integer.toString(mid) + "," + Integer.toString(a) +");";
                sqLiteDatabase.execSQL(query);*/
            }


            String q = "select * from " + loginDatabaseHelper.TABLE_MessMember + ";";
            Cursor cursor = sqLiteDatabase.rawQuery(q,null);
            if(cursor==null)
                Log.e("he","in array cursor null");
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {

                String tuple=cursor.getString(0)+" "+cursor.getString(1)+"  "+cursor.getString(2) +" "+ cursor.getString(3)+"  "+cursor.getString(4)+"  "+
                        cursor.getString(5)+"  "+cursor.getString(6)+"  "+cursor.getString(7)+"  "+cursor.getString(8)+"  "+cursor.getString(9) +" "+cursor.getString(10);

                Log.e("Member table",tuple);
                cursor.moveToNext();
            }

            String qt = "select * from " + loginDatabaseHelper.TABLE_MessMember_Group + ";";
            Cursor cursor1 = sqLiteDatabase.rawQuery(qt,null);
            if(cursor1==null)
                Log.e("he","in array cursor null");
            cursor1.moveToFirst();
            while(!cursor1.isAfterLast())
            {

                String tuple1=Integer.toString(cursor1.getInt(0));
                String tuple=Integer.toString(cursor1.getInt(0))+ " " +Integer.toString(cursor1.getInt(1));
                Log.e("Membergroup mid",tuple1);
                Log.e("Membergroup table",tuple);
                cursor1.moveToNext();
            }

            Log.e("done","done");
            NavUtils.navigateUpFromSameTask(this);
        }

        if(id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }


        return super.onOptionsItemSelected(item);
    }



    public void displayDate()
    {
        Spinner spinner = (Spinner)findViewById(R.id.dayspin);
        spinner.setSelection(day - 1);
    }

    public void addGroups(View view)
    {
        Groupdialog groupdialog = new Groupdialog();
        groupdialog.show(this.getSupportFragmentManager(), "hello");
    }

    public void addRate(View view)
    {
        Ratedialog ratedialog= new Ratedialog();
        ratedialog.show(this.getSupportFragmentManager(), "hello");
    }



    public static class Groupdialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the dialog title
            final ArrayList<Integer> mSelectedItems = new ArrayList<Integer>();
            AlertDialog.Builder builder1 = builder.setTitle("Add Groups")
                    .setMultiChoiceItems(getCursor(),"group_name", "group_name", new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if (isChecked) {
                                // If the user checked the item, add it to the selected items
                                // Log.d("value of which", Integer.toString(which));
                                if (mSelectedItems.contains(which));
                                    // Else, if the item is already in the array, remove it
                                    //mSelectedItems.remove(Integer.valueOf(which));
                                else
                                    mSelectedItems.add(which);
                            } else if (mSelectedItems.contains(which)) {
                                // Else, if the item is already in the array, remove it
                                mSelectedItems.remove(Integer.valueOf(which));
                            }

                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK, so save the mSelectedItems results somewhere
                            // or return them to the component that opened the dialog
                            CreateMember.selectedItms = new ArrayList<Integer>(mSelectedItems);



                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

            return builder.create();
        }

        public Cursor getCursor()
        {
            String query="select * from "+LoginDatabaseHelper.TABLE_Group+" ;";
            Log.e("helper",query);
            Cursor cursor=sqLiteDatabase.rawQuery(query,null);
            return cursor;
        }
    }

    public static class Ratedialog extends DialogFragment {
        int pos;
        int rid;
        int ct =1;
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the dialog title
            AlertDialog.Builder builder1 = builder.setTitle("Set Rate")
                    .setSingleChoiceItems(getCursor(),pos,"category", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Log.d("rate pos ",Integer.toString(which));
                            rid = which;
                            //Log.d("rate pos id ",Integer.toString(rid));
                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK, so save the mSelectedItems results somewhere
                            // or return them to the component that opened the dialog
                            //Log.d("rate pos id ",Integer.toString(rid));
                            CreateMember.rate_id = rid + 1;
                            //Log.d("cr rate_id",Integer.toString(CreateMember.rate_id));
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

            return builder.create();
        }

        public Cursor getCursor()
        {
            Cursor cursor=new RateDataBase(getContext()).getRateTable();
            return cursor;
        }
    }



   /* public  void setRateSpinner()
    {
        ArrayList<String> my_array = new ArrayList<String>();
        my_array = getTableValues();
        Spinner My_spinner = (Spinner) findViewById(R.id.rate_spinner);
        ArrayAdapter my_Adapter = new ArrayAdapter(this, R.layout.rate_spinner_row,
                my_array);
        My_spinner.setAdapter(my_Adapter);

    }*/


    public ArrayList<String> getTableValues() {
        Log.e("he","in array gettabel");
        ArrayList<String> my_array = new ArrayList<>();
        Cursor cursor=new RateDataBase(this).getRateTable();
        if(cursor==null)
            Log.e("he","in array cursor null");
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {

            String rate=cursor.getString(1)+" : "+cursor.getString(2);

            my_array.add(rate);
            cursor.moveToNext();
        }
        return my_array;
    }

}

