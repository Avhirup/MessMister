package com.example.android.login;

/**
 * Created by medha on 14/11/15.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by medha on 26/10/15.
 */
public  class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.viewHolder>{

    public Context context1;
    List<String> list = new ArrayList<>();
    int position;

    LayoutInflater inflater;
    public RecycleAdapter(Context context)
    {
        context1 = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if(position == 4)
            view = inflater.inflate(R.layout.group_fragment, parent,false);
        else
            view = inflater.inflate(R.layout.listitem, parent,false);
        viewHolder vHolder = new viewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {


        holder.textView.setText(list.get(position));
        holder.imageButton.isShown();
    }

    @Override
    public int getItemCount() {
        return list.size();
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

    public void setList()
    {
        if(position == 1) {
            this.list.add(new String("Medha Naik"));
            this.list.add(new String("Ambika Kale"));
            this.list.add(new String("Manjusha Pednekar"));
            this.list.add(new String("Tanmayee Chinchlikar"));
            this.list.add(new String("Isha Gulavani"));
            this.list.add(new String("Tejal Sarda"));
            this.list.add(new String("Ambika Kale"));
            this.list.add(new String("Manjusha Pednekar"));
            this.list.add(new String("Tanmayee Chinchlikar"));
            this.list.add(new String("item1"));
        }
        else if(position == 2)
        {
            this.list.add(new String("Isha Gulavani"));
            this.list.add(new String("Tejal Sarda"));
            this.list.add(new String("Ambika Kale"));
            this.list.add(new String("Manjusha Pednekar"));
            this.list.add(new String("Tanmayee Chinchlikar"));
            this.list.add(new String("item1"));
        }
        else if(position == 3)
        {

            this.list.add(new String("Tejal Sarda"));
            this.list.add(new String("Ambika Kale"));
            this.list.add(new String("Manjusha Pednekar"));
            this.list.add(new String("Tanmayee Chinchlikar"));
            this.list.add(new String("item1"));
        }
        else if (position == 4)
        {
            this.list = new  MemberDatabase(context1).getAllMembers();

        }

    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView textView;
        ImageButton imageButton;


        public viewHolder(View itemView) {


            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.member_name);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context1,textView.getText(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context1, MemberDescription.class);
                    context1.startActivity(intent);

                }
            });
            imageButton=(ImageButton)itemView.findViewById(R.id.menu_button);
            imageButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Log.e("click", "button");
            if(position == 1) {
                PopupMenu popupMenu = new PopupMenu(context1, imageButton);
                popupMenu.inflate(R.menu.all_member_menu);
                popupMenu.show();
            }
        }
    }



}
