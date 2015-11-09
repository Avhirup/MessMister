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
        setRateSpinner();

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
            NavUtils.navigateUpFromSameTask(this);
        }

        if(id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }


        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
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



    public static class Groupdialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the dialog title
            AlertDialog.Builder builder1 = builder.setTitle("Groups")
                    .setMultiChoiceItems(getCursor(),"group_name", "group_name", new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                               //

                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK, so save the mSelectedItems results somewhere
                            // or return them to the component that opened the dialog

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


    public  void setRateSpinner()
    {
        ArrayList<String> my_array ;
        my_array = getTableValues();
        Spinner My_spinner = (Spinner) findViewById(R.id.rate_spinner);
        ArrayAdapter my_Adapter = new ArrayAdapter(this, R.layout.rate_spinner_row,
                my_array);
        My_spinner.setAdapter(my_Adapter);

    }


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

