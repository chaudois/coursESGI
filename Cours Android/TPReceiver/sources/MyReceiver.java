package com.damie.tpreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    int cpt=1;
    @Override
    public void onReceive(Context context, Intent intent) {
        cpt++;

        PendingIntent activityLauncher=//Intent latent (en attente )
                PendingIntent.getActivity(context
                                            ,0
                                                        //ici context est l'origine du signal
                                            ,new Intent(context,MainActivity.class)//su declanchement de cet Intent latent, déclenche cet action
                                            ,PendingIntent.FLAG_UPDATE_CURRENT);

        Toast.makeText(context,//affiche la popup
                "Received : " + intent.getAction(),
                Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder notifBuilder=new NotificationCompat.Builder(context);//creer un constructeur de notif


        Notification notification=notifBuilder
                .setContentTitle("Received Notif")//titre
                .setContentIntent(activityLauncher)//l'Intent latent qui va se declencher lors du click sur la notif
                .setContentText(intent.getAction())//set le message de la notif
                .setSmallIcon(R.mipmap.ic_launcher_round)//l'icone de la notif
                .setAutoCancel(true)//si on fait une action sur la notif, est ce que la notif disparait?
                .build();//on fabrique la notif, mais on ne l'affiche pas encore

        NotificationManager notificationManager=//récuperation d'un objet pour manager nos notif
                (NotificationManager) context//on le recupère du context
                .getSystemService(Context.NOTIFICATION_SERVICE);//on précise celui qu'on veut recuperer

        notificationManager.notify(cpt,notification);//affiche la notif
    }
}
