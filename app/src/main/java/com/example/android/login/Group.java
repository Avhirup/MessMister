package com.example.android.login;

/**
 * Created by avhirup on 10/27/2015.
 */
public class Group {

    //Declarations
    private int group_id;
    private String group_name;
    //Method Implementation

    //Constructor
    public Group() {
        this.group_name="";
    }

    public Group(String group_name)
    {
        this.group_name=group_name;
    }

    //getters & setters
    public int getGroupID() {
        return group_id;
    }

    public String getGroupName() {
        return group_name;
    }

    public void setGroupName(String group_name){
        this.group_name=group_name;
    }
}
