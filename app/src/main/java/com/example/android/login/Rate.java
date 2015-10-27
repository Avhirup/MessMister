package com.example.android.login;

/**
 * Created by avhirup on 10/27/2015.
 */
public class Rate {

    //Declarations
    private String category;
    private int duration;
    private int rate_id;


    //Constructors

    public Rate() {
        this.category="";
        duration="";
    }
    public Rate(String category,int duration)
    {
        this.duration=duration;
        this.category=category;
    }
    //Methods
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category=category;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration=duration;
    }

    public int getRate() {
        return rate_id;
    }

    /**
     * @param rate
     */
    public void setRate(int rate) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Integer getRateId() {
        // TODO implement here
        return null;
    }
}
