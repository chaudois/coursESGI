package com.damie.tpservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static final String MAIN_ACTIVITY = "MAIN_ACTIVITY" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(MAIN_ACTIVITY,"onCreate");
    }
    public void startDownload(View vue){
        Intent intent = new Intent(this, DownloaderService.class);
        startService(intent);//démarre le serviceDownloaderService
        Log.i(MAIN_ACTIVITY,"startDownload");

    }
    public void stopDownload(View vue){
        Intent intent = new Intent(this, DownloaderService.class);
        stopService(intent);//arret du service DownloaderService
        Log.i(MAIN_ACTIVITY,"stopDownload");


    }
    ServiceConnection sc=new ServiceConnection() {//instanciation de l'interface en overidant ses methodes
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {//une fois la connexion assuré, déclanche cette fonction
            connected=true;
            dlBinder=(DownloaderService.DownloaderBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {//une fois la deconexion engagé
            connected=false;
        }
    };

    private boolean connected=false;

    private DownloaderService.DownloaderBinder dlBinder;

    public void connect(View vue){
        if(!connected){
            Intent intent=new Intent(this,DownloaderService.class);//précise à quelle service on voudra se binder
            bindService(//connection au service downloaderService
                    intent//quelle service?
                    ,sc//quelle callBack de connexion
                    ,BIND_AUTO_CREATE);//si le service n'est pas créé, le créer
        }else{
            long nbByte=dlBinder.getDownLoadedNbByte();
            Toast.makeText(this," => "+nbByte,Toast.LENGTH_SHORT).show();
        }
        Intent intent=new Intent(this, DownloaderService.class);
        bindService(intent,sc,BIND_AUTO_CREATE);
    }
    public void disconnect(View vue){
        unbindService(sc);
    }

}
