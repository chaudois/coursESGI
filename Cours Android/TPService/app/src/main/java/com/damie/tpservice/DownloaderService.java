package com.damie.tpservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class DownloaderService extends Service {
    private static final String DOWNLOADER_SERVICE = "DOWNLOADER_SERVICE";

    DoawnLoaderThread dl;
    public DownloaderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {//dès qu'une activité se connect au service
        return new DownloaderBinder();//retourne un objet qui permet de faire la liason entre l'activité et le service

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(DOWNLOADER_SERVICE,"onUnBind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//au lancement du service
        Log.i(DOWNLOADER_SERVICE,"onStartCommand");

        dl=new DoawnLoaderThread();//instanciation du thread de DL
        dl.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//appelé sur stopService de mainActivity
        super.onDestroy();
        Log.i(DOWNLOADER_SERVICE,"onDestroy");
        dl.interrupt();
    }


    public class DownloaderBinder extends Binder {
        public long getDownLoadedNbByte(){
            return dl.getNbByte().get();
        }
    }
}
