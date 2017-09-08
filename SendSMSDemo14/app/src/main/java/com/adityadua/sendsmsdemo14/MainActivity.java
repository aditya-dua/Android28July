package com.adityadua.sendsmsdemo14;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText phnEdt,msgEdt;
    TextView status;
    Button submit,reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phnEdt = (EditText)findViewById(R.id.editText);
        msgEdt = (EditText)findViewById(R.id.editText2);

        status = (TextView)findViewById(R.id.textView4);

        submit = (Button)findViewById(R.id.button);
        reset = (Button)findViewById(R.id.button2);

        submit.setOnClickListener(this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Requesting for permission", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},123);
            submit.setEnabled(true);
        }else{
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {
        String phoneNumber = phnEdt.getText().toString();
        String message = msgEdt.getText().toString();

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber,null,message,null,null);
        status.setText(status.getText().toString()+" SMS Sent");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 123){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                submit.setEnabled(true);
            }else{
                Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show();
                submit.setEnabled(false);
                status.setText(status.getText().toString()+" Permission Not Available");
            }
        }

    }
}
