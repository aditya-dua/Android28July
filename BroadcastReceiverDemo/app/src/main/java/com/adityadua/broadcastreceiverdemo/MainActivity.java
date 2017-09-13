package com.adityadua.broadcastreceiverdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Service {
    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

    @Override
    public void onCreate() {
        super.onCreate();

        mp= MediaPlayer.create(getApplicationContext(),R.raw.song);
        mp.start();


    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        mp.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);

    }*/
}
