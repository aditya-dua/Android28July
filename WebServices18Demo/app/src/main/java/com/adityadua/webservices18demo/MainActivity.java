package com.adityadua.webservices18demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.adityadua.webservices18demo.adaptor.DataAdaptor;
import com.adityadua.webservices18demo.models.DataHandler;
import com.adityadua.webservices18demo.network.CallAddr;
import com.adityadua.webservices18demo.network.NetworkStatus;
import com.adityadua.webservices18demo.utils.CommonUtilities;
import com.adityadua.webservices18demo.utils.OnWebServiceResult;
import com.squareup.okhttp.FormEncodingBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWebServiceResult{

    String url="http://api.themoviedb.org/3/movie/tt0816692/credits?api_key=8496be0b2149805afa458ab8ec27560c";
    List<DataHandler> model = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        hitRequest();

    }

    private void hitRequest(){
        FormEncodingBuilder parameters = new FormEncodingBuilder();
        parameters.add("page","1");

        if(NetworkStatus.getInstance(this).isConnectedToInternet()){
            CallAddr call = new CallAddr(url,this,parameters,this, CommonUtilities.SERVICE_TYPE.GET_DATA);
            call.execute();
        }else{
            Toast.makeText(this, "Not connecetd to Internet", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {
        Log.i("response","Result is::"+result);

        try{
            JSONObject obj = new JSONObject(result);
            JSONArray arr = obj.getJSONArray("cast");

            for(int i=0;i<arr.length();i++){
                JSONObject jObj = arr.getJSONObject(i);

                DataHandler handler = new DataHandler();
                handler.setCast_id(jObj.getInt("cast_id"));
                handler.setOrder(jObj.getInt("order"));
                handler.setName(jObj.getString("name"));
                handler.setCharacter(jObj.getString("character"));
                model.add(handler);
            }
            DataAdaptor adaptor = new DataAdaptor(this,model);
            recyclerView.setAdapter(adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
