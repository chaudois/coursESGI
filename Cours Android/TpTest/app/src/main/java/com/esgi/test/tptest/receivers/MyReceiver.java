package com.esgi.test.tptest.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.esgi.test.tptest.R;

import java.util.Random;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(context);
        Notification notif = notifBuilder.
                setSmallIcon(R.mipmap.ic_launcher).
                setContentTitle("Tp Test").
                setContentText("New Max Value !! => " + intent.getIntExtra("MAX", 0)).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        int notifId=new Random().nextInt();
        notificationManager.notify(notifId,notif);
    }
}
