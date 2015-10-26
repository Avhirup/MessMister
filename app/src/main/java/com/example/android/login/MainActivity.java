package com.example.android.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton button;
    LoginDatabaseHelper helper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Toolbar m_toolbar = (Toolbar) findViewById(R.id.t_bar);
        setSupportActionBar(m_toolbar);
        button = (FloatingActionButton)findViewById(R.id.fabBtn);
        button.hide();
        final EditText myEditText = (EditText)findViewById(R.id.password);
        myEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                button = (FloatingActionButton) findViewById(R.id.fabBtn);
                if (hasFocus) {
                    button.show();
                } else if(myEditText.getText().toString().equals("")) {
                    button.hide();

                }
            }
        });
        setupUI(findViewById(R.id.parent));
        helper = new LoginDatabaseHelper(this, "LOGIN_DB", null, 1);
        db = helper.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                   // button.hide();
                    EditText name = (EditText)findViewById(R.id.username);
                    EditText pass = (EditText)findViewById(R.id.password);
                    name.clearFocus();
                    pass.clearFocus();
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public void sign_in(View view)
    {

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        Cursor cursor = db.rawQuery("select* from login", null);
        cursor.moveToFirst();
        Log.i("cursor", cursor.getCount() + "");
        Log.i("values", cursor.getString(1) + " " + cursor.getString(2));
        if(cursor.getString(1).equals(username.getText().toString()) && cursor.getString(2).equals(password.getText().toString()))
        {
            Intent intent = new Intent(this, homepage.class);
            startActivity(intent);
            cursor.close();
            finish();
        }



    }

}
