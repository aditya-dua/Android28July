package com.adityadua.thirdpartylibrariesdemo;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://vignette4.wikia.nocookie.net/kungfupanda/images/7/7b/Lei-lei.jpg/revision/latest?cb=20151104195038";

        ImageView image = (ImageView)findViewById(R.id.imageView);

        Glide.with(this).load(url)
                .thumbnail(.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);

        Button frescoBtn = (Button)findViewById(R.id.button);
        frescoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
