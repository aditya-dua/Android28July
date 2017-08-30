package com.adityadua.thirdpartyintergration21demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by AdityaDua on 17/08/17.
 */

public class GlideActivity  extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_layout_file);

        String url ="http://vignette4.wikia.nocookie.net/kungfupanda/images/7/7b/Lei-lei.jpg/revision/latest?cb=20151104195038";

        ImageView imgV= (ImageView)findViewById(R.id.imageView);

        Glide.with(this)
                .load(url)
                .crossFade()
                .into(imgV);

    }
}
