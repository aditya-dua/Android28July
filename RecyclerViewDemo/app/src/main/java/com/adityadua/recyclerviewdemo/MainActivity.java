package com.adityadua.recyclerviewdemo;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar1);

        if(toolbar !=null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);

        ItemData itemData[] = {

                new ItemData("Kitkat",R.drawable.kitkat),
                new ItemData("Marshmallow",R.drawable.marshmallow),
                new ItemData("Oreo",R.drawable.oreo)
        };


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdaptor adaptor = new MyAdaptor(itemData);

        recyclerView.setAdapter(adaptor);
        //recyclerView.setAnimation(new DefaultItemAnimator());
    }
}
