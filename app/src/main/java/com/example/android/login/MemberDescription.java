package com.example.android.login;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.lang.reflect.Member;
import java.sql.Date;
import java.util.Calendar;
import android.net.Uri;

public class MemberDescription extends AppCompatActivity {
    String name;
    MessMember m;
    int mid;
    MemberDatabase memberDatabase;
    TextView phone;
    ToggleButton togglebtn;
    TextView pvalue;
    TextView amtval;
    TextView startmval;
    TextView jdateval;
    ImageButton phoneButton;
    String mphone;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        memberDatabase =  new MemberDatabase(this);
        Intent intent = getIntent();
         name  = intent.getStringExtra("name");
        getSupportActionBar().setTitle(name);


        phone = (TextView)findViewById(R.id.phone);
        pvalue = (TextView)findViewById(R.id.pvalue);
        amtval= (TextView)findViewById(R.id.amtval);
        startmval = (TextView)findViewById(R.id.startmval);
        jdateval = (TextView)findViewById(R.id.jdateval);
        togglebtn = (ToggleButton)findViewById(R.id.togglebtn);

        m = new MessMember();
        m.setName(name);

         mid = memberDatabase.getMemberId(m);

        mphone = memberDatabase.getPhone(mid);
        int dueamt = memberDatabase.getdue_amt(mid);

        String pstatus;
        if(dueamt == 0)
            pstatus = "PAID";
        else
            pstatus = "NOT PAID";

        String stdate = memberDatabase.getMemberstartdate(mid);
        String stmonth = memberDatabase.getStartmonth(mid);
        int day = 0;
            try {
                Date Sdate = Date.valueOf(stmonth);
                Calendar cal = Calendar.getInstance();
                cal.setTime(Sdate);
                day = cal.get(Calendar.DAY_OF_MONTH);
            }
            catch (Exception e){}

        int isactive = memberDatabase.getMemberIsActive(mid);
        String status;
        if(isactive==0)
          togglebtn.setChecked(false);
        else
           togglebtn.setChecked(true);

       // togglebtn.
        phone.setText(mphone);
        pvalue.setText(pstatus);
        amtval.setText(Integer.toString(dueamt));
        startmval.setText(Integer.toString(day));
        jdateval.setText(stdate);

        phoneButton = (ImageButton)findViewById(R.id.phoneButton);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mphone));
                callIntent.setPackage("com.android.server.telecom");
                startActivity(callIntent);

            }
        });

        ImageButton messageButton = (ImageButton)findViewById(R.id.messageButton);
        messageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void setActiveStatus(View view)
    {
        togglebtn = (ToggleButton)findViewById(R.id.togglebtn);

        if(togglebtn.isChecked())
        {
            memberDatabase.setActive(mid);
        }
        else
            memberDatabase.setInActive(mid);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_member_description, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {
            new MemberDatabase(getApplicationContext()).delete(mid);
            Intent intent = new Intent(this, Members.class);
            startActivity(intent);
            finish();
            return true;
        }
        if(id== R.id.action_add_period)
        {
           ExtendPeriod extendPeriod = new ExtendPeriod(this,1);
            extendPeriod.show();
           finish();
        }

        if(id == R.id.action_add_fees)
        {
            MyDialog addFee = new MyDialog(this, 0);
            addFee.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void editMember(View view)
    {
        Intent intent = new Intent(this, EditMember.class);
        intent.putExtra("name",name);
        startActivity(intent);
        finish();
    }


}
