package com.adityadua.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn;
    Tracker mTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Activity Loaded will start the Anyalytics now", Toast.LENGTH_SHORT).show();

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Button btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(this);
       /* Error:A problem occurred configuring project ':app'.
                > Could not resolve all dependencies for configuration ':app:_debugApkCopy'.
                > Could not find com.google.android.gms:play-services-analytics:10.2.4.
                Required by:
        TestApp:app:unspecified*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Screen Change", "Setting screen name: MainActivity Goes Away");
        mTracker.setScreenName("Image~ SecondActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Action should be captured", Toast.LENGTH_SHORT).show();
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
    }
}
