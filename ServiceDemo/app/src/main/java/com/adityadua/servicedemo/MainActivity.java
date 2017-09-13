package com.adityadua.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button play=(Button)findViewById(R.id.button);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play.getText().toString().equals("Play")) {
                    Intent i = new Intent(MainActivity.this, MyService.class);
                    startService(i);
                    play.setText("Stop");
                }else if(play.getText().toString().equals("Stop")){
                    Intent i = new Intent(MainActivity.this,MyService.class);
                    stopService(i);
                    play.setText("Play");
                }
            }
        });

        Button stop = (Button)findViewById(R.id.button2);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MyService.class);
                stopService(i);
            }
        });
    }
}
