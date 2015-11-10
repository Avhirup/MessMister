package com.example.android.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Members extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout d;
    ActionBarDrawerToggle drawerToggle;
    private int selectionId;
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
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
        tabLayout = (TabLayout)findViewById(R.id.t_layout);
        viewPager = (ViewPager)findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_members, menu);
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
            intent = new Intent(this, homepage.class);
        }
        else if(menuItem.getItemId() == R.id.item2)
        {
            d.closeDrawer(GravityCompat.START);
            return true;
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





    public void create(View view)
    {
        Intent intent = new Intent(this, CreateMember.class);
        startActivity(intent);
    }


    public class PagerAdapter extends FragmentStatePagerAdapter
    {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            MemberFragment memberFragment;
            if(getPageTitle(position).toString().equals("LATE"))
                memberFragment = MemberFragment.newInstance(position + 1);
            else
                memberFragment = MemberFragment.newInstance(position + 1);
            return  memberFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0)
                return "LATE";
            else if(position == 1)
                return "DUE";
            else if(position == 2)
                return "ALL";
            else
                return "GROUPS";
        }
    }





}



