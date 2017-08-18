package com.adityadua.menuexample9demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button optionBtn,contextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optionBtn = (Button)findViewById(R.id.button);
        optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,OptionMenuExample.class);
                startActivity(i);
            }
        });

        contextBtn = (Button)findViewById(R.id.button2);
        contextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ContextMenuExample.class);

                startActivity(i);
            }
        });
    }
}
