package com.example.android.login;

/**
 * Created by avhirup on 10/27/2015.
 */
public class Expense{

    //Declarations
    private int expense_id;
    private String expense_name;
    private int amount;
    //Constructors

    public Expense()
    {
        expense_name="";
        amount=0;
    }
    public Expense(String expense_name,int amount)
    {
        this.expense_name=expense_name;
        this.amount=amount;
    }
    //Methods

    public int getExpId()
    {
        return expense_id;
    }
    public void setExpName(String name) {
        expense_name=name;
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

}
