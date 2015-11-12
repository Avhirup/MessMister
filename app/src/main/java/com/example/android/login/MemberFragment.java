package com.example.android.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by medha on 14/11/15.
 */
public class MemberFragment extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    int position;
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;

    public MemberFragment()
    {

    }

    public static MemberFragment newInstance(int position)
    {
        MemberFragment memberFragment = new MemberFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        memberFragment.setArguments(args);
        return memberFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.member_list, container, false);
        recycleAdapter = new RecycleAdapter(getContext());
        position = getArguments().getInt(ARG_SECTION_NUMBER);
        recycleAdapter.setPosition(position);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycle_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recycleAdapter);
        return view;
    }


}
