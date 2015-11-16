package com.example.android.login;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by medha on 27/10/15.
 */
public class Income {
    //Declarations
    private String date;
    private String income_name;
    private int amount;
    //Constructors
    public Income()
    {
        income_name="";
        amount=0;
    }
    public Income(String income_name,int amount)
    {
        this.income_name=income_name;
        this.amount=amount;
    }

    //Methods

    public void setIncomeName(String name) {
        income_name=name;
    }

    public String getExpName() {
        return expense_name;
    }

    public int getExpID() {
        return expense_id;
    }

    public void setAmount(int amount) {
        this.amount=amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate()
    {
        return date;
    }
    public void setDate()
    {

    }
}
