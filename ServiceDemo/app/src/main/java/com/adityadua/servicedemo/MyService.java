package com.adityadua.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by AdityaDua on 11/09/17.
 */

public class MyService extends Service {

    MediaPlayer mp;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(getApplicationContext(),R.raw.taare);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        //mp.setLooping(true);
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mp.isPlaying()){
            mp.pause();
        }
        mp.release();
    }
}
