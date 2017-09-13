package com.example.shriyanshu.testingexampleandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnResult(View view) {
        EditText ed_TestingText =
                (EditText) findViewById(R.id.ed_TestingText);

        String actual = ed_TestingText.getText().toString();
        String expected = String.format("Hello, %s!", actual);

        TextView tv_ExpectingText =
                (TextView) findViewById(R.id.tv_ExpectingText);

        tv_ExpectingText.setText(expected);
    }

}
