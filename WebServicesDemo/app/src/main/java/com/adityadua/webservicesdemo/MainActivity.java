package com.adityadua.webservicesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.adityadua.webservicesdemo.adaptor.DataAdaptor;
import com.adityadua.webservicesdemo.model.DataHandler;
import com.adityadua.webservicesdemo.network.CallAddr;
import com.adityadua.webservicesdemo.network.NetworkStatus;
import com.adityadua.webservicesdemo.network.OnWebServiceResult;
import com.squareup.okhttp.FormEncodingBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWebServiceResult{

    // url = "http://api.themoviedb.org/3/movie/tt0816692/credits?api_key=8496be0b2149805afa458ab8ec27560c";

    String url = "http://api.themoviedb.org/3/movie/tt0816692/credits?api_key=8496be0b2149805afa458ab8ec27560c";

    List<DataHandler> model = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        hitRequest();
    }

    public  void hitRequest(){
        FormEncodingBuilder parameters= new FormEncodingBuilder();
        parameters.add("page","1");

        if(NetworkStatus.getInstance(this).isOnline(this)){
            CallAddr call = new CallAddr(this,url,parameters,this, CommonUtilities.SERVICE_TYPE.GET_DATA);
            call.execute();
        }else {
            Toast.makeText(this, "Not conneced to internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {
        Log.e("result is:","type = "+type+"  result = "+result);

        try{
            JSONObject obj= new JSONObject(result);
            JSONArray arr = obj.getJSONArray("cast");
            for(int i=0;i<arr.length();i++){
                JSONObject jObj= arr.getJSONObject(i);
                DataHandler data = new DataHandler();
                data.setCast_id(jObj.getInt("cast_id"));
                data.setCast_id(jObj.getInt("order"));
                data.setCharacter(jObj.getString("character"));
                data.setName(jObj.getString("name"));
                model.add(data);

            }

            DataAdaptor adaptor = new DataAdaptor(this,model);
            recyclerView.setAdapter(adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
