package com.example.android.login;

import android.app.*;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;


public class NotificationService extends IntentService {

    private final static int PERIOD=10*60*1000;

    public NotificationService() {
        super("NotificationService");
    }

    public  void notificationGenerator() {
        Cursor cursor = getDueMember();
        while (!cursor.isAfterLast()) {

            String memberid = "";
            String membername = "";
            String dueamount = "";


            NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
            notification.setAutoCancel(true);
            notification.setTicker("Late :" + membername);
            notification.setSmallIcon(R.drawable.businessman267);
            notification.setContentTitle("MEMBER: " + membername);
            notification.setContentText("Due Amount: " + dueamount);


            Intent intent = new Intent();
            intent.setAction("SNOOZE");
            PendingIntent snoozeIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.addAction(new NotificationCompat.Action(R.drawable.alarm16, "Snooze", snoozeIntent));


            Intent whatsapp = new Intent();
            whatsapp.setAction("WHATSAPPACTION");
            PendingIntent pendingwhatsappIntent = PendingIntent.getBroadcast(this, 12345, whatsapp, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.addAction(R.drawable.whatsapp16, "Whatsapp", pendingwhatsappIntent);

            notification.setAutoCancel(true);
            android.app.Notification n = notification.build();
            NotificationManagerCompat.from(this).notify(0, n);

            cursor.moveToNext();

            try {
                Thread.sleep(PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        notificationGenerator();
    }




}
