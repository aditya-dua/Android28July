package com.adityadua.intent6demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AdityaDua on 11/08/17.
 */

public class SecondAcivity extends Activity {

    Button browswerBtn,dailBtn,cameraBtn;
    EditText edt,numberEdt;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_layout);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String val = b.getString("key");
        String value = i.getStringExtra("user");

        TextView tv = (TextView)findViewById(R.id.textView2);
        tv.setText("Welcome "+value+",");

        browswerBtn = (Button)findViewById(R.id.webpageBtn);
        edt=(EditText)findViewById(R.id.editText3);
        dailBtn = (Button)findViewById(R.id.button2);
        numberEdt = (EditText)findViewById(R.id.editText4);
        cameraBtn = (Button)findViewById(R.id.button3);
        iv=(ImageView)findViewById(R.id.imageView);
        browswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implict Intent
                String url = edt.getText().toString();
                if(url.startsWith("http")){}
                else{url="http://"+url;}
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                //i.putExtras(b);
                startActivity(i);
            }
        });

        dailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "tel:"+numberEdt.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(number));
                startActivity(i);
            }
        });
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode==RESULT_OK){

                Bundle extraBundle = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extraBundle.get("data");
                iv.setImageBitmap(imageBitmap);
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Action Stopped in Middle", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
