package com.adityadua.thirdpartylibrariesdemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by AdityaDua on 25/09/17.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.second_main);
        Uri imageUri = Uri.parse("https://i.imgur.com/tGbaZCY.jpg");

        SimpleDraweeView draweeView = (SimpleDraweeView)findViewById(R.id.sdvImage);
        draweeView.setImageURI(imageUri);

        //https://i.imgur.com/tGbaZCY.jpg
    }
}
