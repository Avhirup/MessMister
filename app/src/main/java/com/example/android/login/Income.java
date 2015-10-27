package com.example.android.login;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by medha on 27/10/15.
 */
public class Income {
    //Declarations
        private  int income_id;
        private int month;
        private int year;
        private HashSet<FeeCollected> fee_collected_set;
        private HashSet<Expense> expenses;
        private int amount_lost;
        private int amount_earned;
        private int profit;

    //Constructors
    public Income()
    {
        month=Calendar.MONTH;
        year=Calendar.YEAR;
        fee_collected_set=new HashSet<FeeCollected>();
        expenses=new HashSet<Expense>();
        amount_lost=0;
        amount_earned=0;
        profit=0;

    }

    //Methods

    public int getIncomeId() {
        return income_id;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getAmountLost() {
            calculateAmountLost();
            return amount_lost;
    }

    public int  getAmountEarned() {
            calculateAmountEarned();
            return amount_earned;
    }

    public int getProfit() {
        calculateProfit();
        return profit;
    }

    private void calculateAmountLost() {
            // TODO implement here
        }

    private void calculateAmountEarned() {
            // TODO implement here
    }

    private void calculateProfit() {
            // TODO implement here
    }
 }
