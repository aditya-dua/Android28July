package com.adityadua.menuexample9demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by AdityaDua on 16/08/17.
 */
// 3 dots , they are in action bar and are provided from AppCompatActivity
// create a main.xml file in res
// Overriding the default method (onCreateOptionsMenu)
// dynamic view which are created in Java nad are to be shown :: "XML/UI"... they are created using the Inflator
// What is the annotation @Nullable?? =>
public class OptionMenuExample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_example);
        getSupportActionBar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.logout){
            finish();
        }else if(id==R.id.setting){
            Toast.makeText(this, "Settings Option", Toast.LENGTH_SHORT).show();
        }else if(id== R.id.profile){
            Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
