package com.adityadua.thirdpartyintergration21demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);


        Uri image=Uri.parse("https://i.imgur.com/tGbaZCY.jpg");

        SimpleDraweeView draweeView = (SimpleDraweeView)findViewById(R.id.sdvImage);
        draweeView.setImageURI(image);

        Button btn =(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,GlideActivity.class);
                startActivity(i);
            }
        });
    }
}
