package com.example.android.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    DrawerLayout d;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    private int selectionId;
    AlertDialog.Builder alert;
    LayoutInflater factory;
    com.getbase.floatingactionbutton.FloatingActionsMenu floatingActionsMenu;



    LoginDatabaseHelper loginDatabaseHelper;
    static SQLiteDatabase sqLiteDatabase=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        factory = LayoutInflater.from(this);
        alert = new AlertDialog.Builder(this);
        toolbar = (Toolbar)findViewById(R.id.t_bar);
        setSupportActionBar(toolbar);
        d = (DrawerLayout) findViewById(R.id.d_lay);
        drawerToggle = new ActionBarDrawerToggle(this, d, toolbar,R.string.closed,R.string.open);
        d.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = (NavigationView)findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState == null)
            selectionId = R.id.item1;
        else
            selectionId = savedInstanceState.getInt("selection");

        loginDatabaseHelper =new LoginDatabaseHelper(this,"LOGIN_DB",null,1);
        sqLiteDatabase=loginDatabaseHelper.getWritableDatabase();

        //Sending Broadcast
        Intent intent=new Intent();
        intent.setAction("NOTIFICATION");
        intent.addFlags(intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
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
            Intent intent =  new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        Intent intent = null;
        menuItem.setChecked(true);
        selectionId = menuItem.getItemId();
        if(menuItem.getItemId() == R.id.item1)
        {
            d.closeDrawer(GravityCompat.START);
            return true;
        }
        else if(menuItem.getItemId() == R.id.item2)
        {
            intent = new Intent(this, Members.class);
        }
        else if(menuItem.getItemId() == R.id.item3)
        {
            intent = new Intent(this, Staff.class);
        }
        else if(menuItem.getItemId() == R.id.item4)
        {
            intent = new Intent(this, Bills.class);
        }
        else if(menuItem.getItemId() == R.id.item5)
        {
            intent = new Intent(this, Balance.class);
        }
        else if(menuItem.getItemId() == R.id.item6)
        {
            intent = new Intent(this, Help.class);
        }
        else if(menuItem.getItemId() == R.id.item7)
        {
            intent = new Intent(this, About.class);
        }
        d.closeDrawer(GravityCompat.START);
        startActivity(intent);
        finish();
        return false;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selection", selectionId);
    }
    @Override
    public void onBackPressed() {
        if(d.isDrawerOpen(GravityCompat.START))
            d.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public void addFee(View view)
    {
        floatingActionsMenu = (com.getbase.floatingactionbutton.FloatingActionsMenu)findViewById(R.id.multiple_actions);
        floatingActionsMenu.toggle();
        final View textEntryView = factory.inflate(R.layout.edittext3,null);
        alert.setView(textEntryView);
        alert.setTitle("Add New Member Fee");
        final MemberDatabase memberDatabase = new MemberDatabase(this);
        final RateDataBase rateDataBase = new RateDataBase(this);

        final AutoCompleteTextView member_fee_name = (AutoCompleteTextView)textEntryView.findViewById(R.id.member_fee_name);
        final AutoCompleteTextView amount_paid = (AutoCompleteTextView)textEntryView.findViewById(R.id.amount_paid);

        Context context = getApplicationContext();
        CharSequence text = "already paid";
        int duration = Toast.LENGTH_SHORT;

        final Toast toast = Toast.makeText(context,text,duration);


        member_fee_name.setThreshold(0);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, new MemberDatabase(this).getAllMembers());

        member_fee_name.setAdapter(adapter);


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try {


                    MessMember m = new MessMember();


                    int mid, rid, amt, dueamt, mdueamt;
                    String name = member_fee_name.getText().toString();
                    String amount = amount_paid.getText().toString();

                    int paidamt = Integer.parseInt(amount);
                    m.setName(name);

                    mid = memberDatabase.getMemberId(m);
                    rid = memberDatabase.getrate_id(mid);


                    amt = rateDataBase.getamt(rid);
                    mdueamt = memberDatabase.getdue_amt(mid);
                    if (mdueamt != 0)
                    {
                        dueamt = mdueamt - paidamt;
                        memberDatabase.setDueamt(mid, dueamt);
                    }
                    else
                    {
                        toast.show();
                    }

            }

            catch(Exception e) {}

        }
    }

        );

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                }

        );
            alert.show();
        }
    }
