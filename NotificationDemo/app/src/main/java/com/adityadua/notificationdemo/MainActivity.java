package com.adityadua.notificationdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{


    Button big_text_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button big_text = (Button)findViewById(R.id.button);
        big_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button :
                // Big Text Notification
                Bitmap icon = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                            .setAutoCancel(true)
                            .setContentText("Big Text Notification Text")
                            .setContentTitle("Big Text Notification")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(icon);


                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();

                bigText.bigText("Professor Jean Twenge from San Diego University, in a recently published book says millennials born between 1995 and 2012 are going on fewer dates than their parents’ generation");
                bigText.setBigContentTitle("Today's News");
                bigText.setSummaryText("By : Aditya Dua");

                mBuilder.setStyle(bigText);

                Intent i = new Intent(this,NextActivity.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

                stackBuilder.addNextIntent(i);

                PendingIntent pi= stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(pi);

                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);


                manager.notify(100,mBuilder.build());



        }
    }
}
