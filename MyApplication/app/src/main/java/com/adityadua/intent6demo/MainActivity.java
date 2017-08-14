package com.adityadua.intent6demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText userName,pwd;
// values are passed as Key Value Pairs.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button)findViewById(R.id.nextBtn);
        userName = (EditText)findViewById(R.id.editText);
        pwd = (EditText)findViewById(R.id.editText2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userNamestr= userName.getText().toString();
                String pwdStr = pwd.getText().toString();
                if(userNamestr.equalsIgnoreCase("aditya") && pwdStr.equals("Aditya")) {
                    // Explict Intent
                    Intent i = new Intent(MainActivity.this, SecondAcivity.class);
                    Bundle b = new Bundle();
                    b.putString("key","1");

                    i.putExtra("user",userNamestr);
                    i.putExtras(b);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                }   
            }
        });
    }
}
