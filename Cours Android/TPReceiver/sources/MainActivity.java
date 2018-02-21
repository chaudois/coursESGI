package com.damie.tpreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver=new MyReceiver();//se declenche dès qu'une action est executé
        //registerReceiver est une fonction de l'activité
        registerReceiver(receiver,//on prend l'objet "trigger" et on l'associe à l'action
                new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));//qui est le AIRPLANE_MODE qui se
                                                                        // trouve dans la variable static de Intent
    }

    public void callReceiver(View vue){
//        Intent intent=new Intent(this, MyReceiver.class);
        Intent intent=new Intent("MY_LITTLE_ACTION");//envoie l'action "MY_LITTLE_ACTION" dans l'Intent
        sendBroadcast(intent);//braodcast le signal stocké dans l'Intent dans le system
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);//quand on detruit l'activité, on "déconnect" le receiver du trigger (qui était le AIRPLANE_MODE)

    }
}
