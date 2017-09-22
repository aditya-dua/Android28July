package com.adityadua.webservicesdemo.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.adityadua.webservicesdemo.CommonUtilities;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okio.Buffer;

/**
 * Created by AdityaDua on 18/09/17.
 */

public class CallAddr extends AsyncTask<String,Void,String> {


    Context context;
    String url;
    String result="";
    FormEncodingBuilder formBody;
    OnWebServiceResult resultListener;
    CommonUtilities.SERVICE_TYPE serviceType;
    Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public CallAddr(Context context, String url, FormEncodingBuilder formBody,
                    OnWebServiceResult resultListener, CommonUtilities.SERVICE_TYPE serviceType) {
        this.context = context;
        this.url = url;
        this.formBody = formBody;
        this.resultListener = resultListener;
        this.serviceType = serviceType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Request sent for WebService", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(120, TimeUnit.SECONDS);
        client.setReadTimeout(120,TimeUnit.SECONDS);

        RequestBody body = formBody.build();
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Log.i("Request Copy :","Url Is :"+url+"Requets Object"+bodyToString(request));

        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()){
                // it will set the JSON here to result object
                result = response.toString();
                if(result.equals("")||result.equals("null")||result.length()==0){
                    Toast.makeText(context, "Error Reported", Toast.LENGTH_SHORT).show();
                }
            }
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, "Result in Logs", Toast.LENGTH_SHORT).show();
        resultListener.getWebResponse(s,serviceType);
    }

    // a method to convert the requets to string
    private static  String bodyToString(final Request request){
        try{
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();

            copy.body().writeTo(buffer);
            return  buffer.readUtf8();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "Error";
    }
}
