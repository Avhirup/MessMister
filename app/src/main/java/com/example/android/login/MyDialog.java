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
    View textEntryView;
    MemberDatabase memberDatabase;
    IncomeDatabase incomeDatabase;
    ExpenseDatabase expenseDatabase;
    RateDataBase rateDataBase;
    AutoCompleteTextView amount_paid;
    AutoCompleteTextView member_fee_name;
    CharSequence text = "already paid";
    Toast toast;
    int duration = Toast.LENGTH_SHORT;
    int type;
    String titles[] = {"  Add New Member Fee","  Add Quick Income", "  Add Quick Expense"};
    public MyDialog(Context context, int type) {
        this.context = context;
        this.type = type;
        factory = LayoutInflater.from(context);
        alert = new AlertDialog.Builder(context);
        textEntryView = factory.inflate(R.layout.edittext3, null);
        alert.setView(textEntryView);
        setTitle();
        memberDatabase = new MemberDatabase(context);
        incomeDatabase = new IncomeDatabase(context);
        expenseDatabase=new ExpenseDatabase(context);
        rateDataBase = new RateDataBase(context);
        member_fee_name = (AutoCompleteTextView) textEntryView.findViewById(R.id.member_fee_name);
        amount_paid = (AutoCompleteTextView) textEntryView.findViewById(R.id.amount_paid);
        toast = Toast.makeText(context, text, duration);
        member_fee_name.setThreshold(0);

        if (type == 0)
            getMemberValues();
        else if(type == 1)
            getIncomeValues();
        else if(type == 2)
            getExpenseValues();


    }



    public void show(){

        alert.show();
    }

    public void setTitle() {
        alert.setTitle(titles[type]);
    }

    public void getMemberValues()
    {
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


    public void getIncomeValues()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_dropdown_item_1line, new IncomeDatabase(context).getAllIncomes());

        member_fee_name.setAdapter(adapter);


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        try {


                            Income income = new Income();
                            String name = member_fee_name.getText().toString();
                            int amount = Integer.parseInt(amount_paid.getText().toString());
                            income.setIncomeName(name);
                            income.setAmount(amount);
                            incomeDatabase.add(income);


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

    public void getExpenseValues()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_dropdown_item_1line, new ExpenseDatabase(context).getAllExpenses());

        member_fee_name.setAdapter(adapter);


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        try {



                            Expense expense = new Expense();
                            String name = member_fee_name.getText().toString();
                            int amount = -Integer.parseInt(amount_paid.getText().toString());
                            expense.setExpenseName(name);
                            expense.setAmount(amount);
                            expenseDatabase.add(expense);

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
    }





