package com.adityadua.broadcastreceiverdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import android.support.v7.app.NotificationCompat.Builder;
import android.widget.Toast;

/**
 * Created by AdityaDua on 13/09/17.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = (Builder) new Builder(context.getApplicationContext())
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("Time has been Changed")
                                        .setContentText("Successful time change");


        builder.setAutoCancel(true);

        Intent resultIntent = new Intent(context,MainActivity.class);


        //context.stopService(resultIntent);
        //PendingIntent resultPI= PendingIntent.getActivity(context,100,resultIntent,0);

       // builder.setContentIntent(resultPI);



        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());

        Toast.makeText(context, "onReceive Called", Toast.LENGTH_SHORT).show();

        // building notification::



       // NotificationCompat.Builder mBuilder = (NotificationCompat.Builder
    }
}
