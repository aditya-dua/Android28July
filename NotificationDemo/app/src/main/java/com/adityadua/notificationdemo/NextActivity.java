package com.adityadua.notificationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by AdityaDua on 13/09/17.
 */

public class NextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "On Next Screen", Toast.LENGTH_SHORT).show();
    }
}
