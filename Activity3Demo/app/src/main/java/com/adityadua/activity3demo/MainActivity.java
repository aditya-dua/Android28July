package com.adityadua.activity3demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// type casting to java ....
        example = (EditText)findViewById(R.id.editText);

        String text = example.getText().toString();
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();

    }
}
