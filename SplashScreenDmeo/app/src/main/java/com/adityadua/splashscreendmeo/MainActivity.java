package com.adityadua.splashscreendmeo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    final int SPLASH_TIME_DEFAULT=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** This code uses thread, this will be explained to you in the threads topics
         *
         * Don't worry if you are not able to follow
         */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(MainActivity.this,DetailedActivity.class);
                MainActivity.this.startActivity(i);
                MainActivity.this.finish();
            }
        },SPLASH_TIME_DEFAULT);
    }
}
