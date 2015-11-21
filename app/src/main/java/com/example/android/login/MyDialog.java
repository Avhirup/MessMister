package com.example.android.login;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

/**
 * Created by medha on 17/11/15.
 */
public class MyDialog
{
    Context context;
    AlertDialog.Builder alert;
    LayoutInflater factory;
    int type;
    String titles[] = {"  Add New Member Fee","  Add Quick Income", "  Add Quick Expense"};
    public MyDialog(Context context, int type)
    {
        this.context = context;
        this.type = type;
        factory = LayoutInflater.from(context);
        alert = new AlertDialog.Builder(context);
        final View textEntryView = factory.inflate(R.layout.edittext3,null);
        alert.setView(textEntryView);
        setTitle();
        final MemberDatabase memberDatabase = new MemberDatabase(context);
        final RateDataBase rateDataBase = new RateDataBase(context);

        final AutoCompleteTextView member_fee_name = (AutoCompleteTextView)textEntryView.findViewById(R.id.member_fee_name);
        final AutoCompleteTextView amount_paid = (AutoCompleteTextView)textEntryView.findViewById(R.id.amount_paid);

        CharSequence text = "already paid";
        int duration = Toast.LENGTH_SHORT;

        final Toast toast = Toast.makeText(context,text,duration);


        member_fee_name.setThreshold(0);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_dropdown_item_1line, new MemberDatabase(context).getAllMembers());

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
                            if (mdueamt != 0) {
                                dueamt = mdueamt - paidamt;
                                memberDatabase.setDueamt(mid, dueamt);
                            } else {
                                toast.show();
                            }

                        } catch (Exception e) {
                        }

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


    }

    public void show(){

        alert.show();
    }

    public void setTitle()
    {
        alert.setTitle(titles[type]);
    }

}
