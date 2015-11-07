package com.example.android.login;

import android.support.v4.app.NotificationCompat;

/**
 * Created by manjush on 27-10-2015.
 */
public class Notification {

    private int notification_id;
    private int member_id;
    private String notifyOn;
    NotificationCompat.Builder notification=new NotificationCompat.Builder(this);

    public Notification()
    {}

    public Notification(int notification_id, int member_id, String notifyOn) {
        this.notification_id = notification_id;
        this.member_id = member_id;
        this.notifyOn = notifyOn;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getNotifyOn() {
        return notifyOn;
    }

    public void setNotifyOn(String notifyOn) {
        this.notifyOn = notifyOn;
    }
}
