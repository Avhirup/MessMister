package com.example.android.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {
    public NotificationReceiver() {
        Log.e("helper","IN NOTIFICATION RECEIVER");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, NotificationService.class));
    }
}
