package com.adityadua.sharedprefexample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName,password;
    Button btn,btn2;
    SharedPreferences sharedPreferences;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // because : editText which is View now I need to use this view as
        // TextVoew , EditText,....
        // I convert to EditText
        userName = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        sharedPreferences = getSharedPreferences("myprefs", Context.MODE_APPEND);
        tv = (TextView)findViewById(R.id.textView2);
        if(sharedPreferences.contains("username")){

            String str = sharedPreferences.getString("username","");
            tv.setText(str);
            tv.setVisibility(TextView.VISIBLE);
        }else{

        }

        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= userName.getText().toString();
                String email = password.getText().toString();


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",name);
                editor.putString("email",email);
                editor.commit();

                userName.setText("");
                password.setText("");

            }
        });

        btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeMeHome();
            }
        });

    }

    public void takeMeHome(){
        final AlertDialog.Builder alertDailogBuilder = new AlertDialog.Builder(this);
        alertDailogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDailogBuilder.setTitle("Exit!");
        alertDailogBuilder.setMessage("Are you sure you want to go HOME!");
        alertDailogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Exiting!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDailogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();

            }
        });
        alertDailogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancel Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        AlertDialog alert = alertDailogBuilder.create();
        alert.show();
    }
}
