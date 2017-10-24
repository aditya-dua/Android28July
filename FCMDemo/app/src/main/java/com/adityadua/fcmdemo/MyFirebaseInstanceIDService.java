package com.adityadua.fcmdemo;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by AdityaDua on 12/10/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static  final String TAG ="MyFirebaseInstanceIDSe";
    public static final String REGISTERATION_SUCCESS = "RegisterationSuccess";


    @Override
    public void onTokenRefresh() {
        String refrhsedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Token Is"+refrhsedToken);
    }
}
