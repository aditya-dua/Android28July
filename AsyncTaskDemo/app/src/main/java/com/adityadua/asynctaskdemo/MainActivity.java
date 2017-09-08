package com.adityadua.asynctaskdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button load,check;
    ImageView image;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.imageView);
        progress = (ProgressBar)findViewById(R.id.progressBar);

        load= (Button)findViewById(R.id.button);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadIconTask().execute(R.drawable.acdglogo);
            }

        });

        check = (Button)findViewById(R.id.button2);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "I'm Working !!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*
        Async TAsk will have 3 parametrs ::
        1... Input
        2... Progress
        3... output


    */
    class LoadIconTask extends AsyncTask<Integer,Integer,Bitmap>{
        @Override
        protected Bitmap doInBackground(Integer[] params) {

            Bitmap tmp = BitmapFactory.decodeResource(getResources(),params[0]);
            // here we will try to show the user that we are loading
            for(int i=0;i<10;i++){
                try {
                    Thread.sleep(1000);
                    publishProgress(i*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return tmp;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progress.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progress.setVisibility(ProgressBar.GONE);
            image.setImageBitmap(bitmap);
        }

        @Override
        protected void onPreExecute() {
            // this method will have access to UI elemnts becoz it works on UII
            super.onPreExecute();
            progress.setVisibility(ProgressBar.VISIBLE);
        }
    }
}
