package com.esgi.test.tptest.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.esgi.test.tptest.R;
import com.esgi.test.tptest.model.SimpleObject;
import com.esgi.test.tptest.touchwithcare.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<SimpleObject> simpleObjects = new ArrayList<>();
    int max = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivity(v);
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThirdActivity();
            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFourthActivity();
            }
        });

    }

    private void startSecondActivity(View v) {
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra(Constants.MAX, max);
        startActivityForResult(intent,Constants.REQUEST_CODE_FOR_SECOND_ACTIVITY);
    }
    private void startThirdActivity() {
        Intent intent = new Intent(this,ThirdActivity.class);
        intent.putExtra(Constants.DATA, (Serializable) simpleObjects);
        startActivity(intent);
    }
    private void startFourthActivity() {
        Intent intent = new Intent(this, FourthActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== Constants.REQUEST_CODE_FOR_SECOND_ACTIVITY){
                if (data != null){
                    int value = data.getIntExtra(Constants.SCORE,0);
                    simpleObjects.add(new SimpleObject(value,new Date()));
                    if(value>max){
                        max=value;
                        Intent intent = new Intent("NEW_MAX_VALUE");
                        intent.putExtra(Constants.MAX,max);
                        sendBroadcast(intent);

                        NotificationManager notificationManager=null;
//                        notificationManager.notify("",1,"new high score");//affiche la notif
                          }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
