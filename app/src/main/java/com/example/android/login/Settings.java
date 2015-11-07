package com.example.android.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.audiofx.BassBoost;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    AlertDialog.Builder alert;
    LayoutInflater factory;
    GroupDatabase groupDatabase;
    RateDataBase rateDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        factory = LayoutInflater.from(this);
        toolbar = (Toolbar)findViewById(R.id.t_bar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.settings_list);
        alert = new AlertDialog.Builder(this);
        listView.setOnItemClickListener(new SettingsListner());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    public class SettingsListner implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(position == 0)
            {

                final View textEntryView = factory.inflate(R.layout.edittext1, null);
                final TextInputLayout input = (TextInputLayout)textEntryView.findViewById(R.id.group_name);
                alert.setView(input);
                alert.setTitle("Create New Group");
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        groupDatabase=new GroupDatabase(getBaseContext());
                        String group_name=input.toString();
                        Group group=new Group(group_name);
                        groupDatabase.add(group);
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        dialog.cancel();
                    }
                });
                alert.show();
            }
            else if(position == 1)
            {
                final View textEntryView = factory.inflate(R.layout.edittext2, null);
                alert.setView(textEntryView);
                alert.setTitle("Create New Rate");
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        AutoCompleteTextView rate_name=(AutoCompleteTextView)findViewById(R.id.rate_name);
                        AutoCompleteTextView amount=(AutoCompleteTextView)findViewById(R.id.amount);

                        String category=rate_name.getText().toString();
                        int amnt=Integer.parseInt(amount.getText().toString());
                        Rate rate=new Rate(category,amnt);

                        rateDataBase=new RateDataBase(getBaseContext());
                        rateDataBase.add(rate);


                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }

        }
    }
}
