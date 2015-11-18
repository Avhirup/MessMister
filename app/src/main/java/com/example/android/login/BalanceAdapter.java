package com.example.android.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by medha on 18/11/15.
 */
public  class BalanceAdapter  extends RecyclerView.Adapter<BalanceAdapter.viewHolder> {

    public Context context1;
    ValuesAdapter valuesAdapter;
    RecyclerView recyclerView;
    List<String> lqwoist = new ArrayList<>();
    int position;

    LayoutInflater inflater;

    public BalanceAdapter(Context context) {
        context1 = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = inflater.inflate(R.layout.monthly_balance, parent, false);
        viewHolder vHolder = new viewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        setList();
        Log.e("pos", position + "");
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setList() {

    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        public viewHolder(View itemView) {


            super(itemView);
            valuesAdapter= new ValuesAdapter(context1);
            valuesAdapter.setPosition(position);
            recyclerView = (RecyclerView)itemView.findViewById(R.id.recycle_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(context1));
            recyclerView.setHasFixedSize(false);
            recyclerView.setAdapter(valuesAdapter);
            Log.e("ops",recyclerView.getScrollState() + "");


        }

        @Override
        public void onClick(View v) {

        }
    }
}
