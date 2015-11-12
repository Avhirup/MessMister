package com.example.android.login;

import android.app.*;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;


public class NotificationService extends IntentService {
    MemberDatabase memberDatabase=null;
    private final static int PERIOD=10*60*1000;

    public NotificationService() {
        super("NotificationService");
        Log.e("In Notification Service", "Database Starting");
        Log.e("In Notification Service","Database Started");
    }

    public  void notificationGenerator() {
        memberDatabase=new MemberDatabase(this);

        Cursor cursor = memberDatabase.getMember();
        cursor.moveToFirst();
        Log.e("In Notification","Notification Cursor");
        while (!cursor.isAfterLast()) {
            int memberid=Integer.parseInt(cursor.getString(0));
            String membername = cursor.getString(1);
            String dueamount = cursor.getString(6);


            NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
            notification.setAutoCancel(true);
            notification.setTicker("Late :" + membername);
            notification.setSmallIcon(R.drawable.businessman267);
            notification.setContentTitle("MEMBER: " + membername);
            notification.setContentText("Due Amount: " + dueamount);


            Intent intent = new Intent();
            intent.setAction("SNOOZE");
            intent.putExtra("memberid",memberid);
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

        /*NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        notification.setTicker("Late :" + "AVHIRUP");
        notification.setSmallIcon(R.drawable.businessman267);
        notification.setContentTitle("MEMBER: " + "AVHIRUP");
        notification.setContentText("Due Amount: " + "1500");
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
        */
    }


    @Override
    protected void onHandleIntent(Intent intent) {
       try
       {
           notificationGenerator();
       }catch (Exception e)
       {
           Log.e("HELPER",e+"");
       }
    }




}
