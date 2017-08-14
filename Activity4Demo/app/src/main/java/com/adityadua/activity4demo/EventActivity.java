package com.adityadua.activity4demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by AdityaDua on 04/08/17.
 */

public class EventActivity extends Activity{

    EditText useredt,pwdedt;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_handling);


        useredt = (EditText)findViewById(R.id.editText);
        pwdedt = (EditText)findViewById(R.id.editText2);
        Log.i("MainActivity:clickEx",useredt.toString());

        loginBtn = (Button)findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// here you can perform any opertaion
                String userName = useredt.getText().toString();
                String password = pwdedt.getText().toString();

                Log.i("MainActivity:onCreate"," "+userName+" "+password );

                if(userName.equalsIgnoreCase("aditya")&& password.equals("aditya")){
                    Toast.makeText(EventActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void clickExmaple(View view){
        Log.i("MainActivity:clickEx","Failure in the starting");

        useredt.setText("");
        pwdedt.setText("");
    }


}
