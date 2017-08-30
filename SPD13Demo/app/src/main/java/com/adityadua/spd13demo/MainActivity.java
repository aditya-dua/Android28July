package com.adityadua.spd13demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // we will have 3 main types :
    // 1. Activity Specific Prefrences : getPrefrences()
    // 2. Application Specific : getSharedPrefrences
    // 3. Android Specific : getDefaultPrefrences()
    // whenever we store the data in form o fkey and value we are reuireed to miantain uniformity in key

    // Intent i
    // when we pass extra :
    /*final String NAME_KEYYY="user";
    i.putExtra(NAME_KEYYY,"aditya");

    Next Class :
    Intent i = getIntent();
    i.getStringExtra(NAME_KEYYY)*/

    EditText userName,email;
    TextView tv;
    Button save,exit;
    SharedPreferences sharedPreferences;

    final String NAME_KEY="UserName";
    final String EMAIL_KEY="Email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.editText);
        email = (EditText)findViewById(R.id.editText2);

        tv = (TextView)findViewById(R.id.textView2);
        sharedPreferences = getPreferences(MODE_APPEND);

        sharedPreferences = getSharedPreferences("myPref",MODE_PRIVATE);
       // get on PrefrencManager
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        fetchPref();

        save = (Button)findViewById(R.id.button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    savePref();
            }
        });
        exit = (Button)findViewById(R.id.button3);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });

    }

    private void savePref(){
        //
        String name= userName.getText().toString();
        String mail = email.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(NAME_KEY,name);
        editor.putString(EMAIL_KEY,mail);

        editor.commit();
        Toast.makeText(this, "Values Saved To Pref", Toast.LENGTH_SHORT).show();

        userName.setText("");
        email.setText("");
        fetchPref();
    }


    private void fetchPref(){
        if(sharedPreferences.contains(NAME_KEY)){
            tv.setVisibility(TextView.VISIBLE);
            tv.setText("Username is "+sharedPreferences.getString(NAME_KEY,""));
        }else{
            tv.setVisibility(TextView.INVISIBLE);
        }
    }

    private void exitApp(){
        // show the dailog Box

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Are you sure you want to Exit!");
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        builder.setPositiveButton("HOME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Exiting......", Toast.LENGTH_SHORT).show();
                finish();
                
            }
        });

        builder.setNegativeButton("STAY Back!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Dailog : No", Toast.LENGTH_SHORT).show();
                // you can even hide the dailog
            }
        });

        builder.setNeutralButton("Clicked by Mistake", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertD= builder.create();
        alertD.show();
    }


}
