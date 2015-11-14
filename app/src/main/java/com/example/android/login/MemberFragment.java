package com.example.android.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by medha on 14/11/15.
 */
public class MemberFragment extends Fragment
{
    public final java.lang.String ARG = "position";
    int position;
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;

    public MemberFragment()
    {

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //TextView textview = new TextView(getActivity());
        //textview.setText("hello " + position);
        View view = inflater.inflate(R.layout.member_list, container, false);
        recycleAdapter = new RecycleAdapter(getContext());
        recyclerView = (RecyclerView)view.findViewById(R.id.recycle_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recycleAdapter);
        return view;
    }


}
