package com.adityadua.webservicesdemo.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by AdityaDua on 15/09/17.
 */

public class NetworkStatus {


    private static NetworkStatus instance = new NetworkStatus();

    static Context context;
    ConnectivityManager connectivityManager;
    boolean connected= false;

    public static  NetworkStatus getInstance(Context ctx){
        context = ctx;
        return instance;
    }


    public boolean isOnline(Context con){
        try{
            connectivityManager = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();

            return connected;
        }catch (Exception e){
            e.printStackTrace();
        }
        return connected;
    }

}
