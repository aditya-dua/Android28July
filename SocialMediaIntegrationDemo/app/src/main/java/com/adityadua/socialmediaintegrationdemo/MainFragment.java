package com.adityadua.socialmediaintegrationdemo;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by AdityaDua on 25/09/17.
 */

public class MainFragment extends Fragment implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    LoginButton loginButton;
    SignInButton signInButton;
    CallbackManager callbackManager;
    ProgressDialog progressDialog;
    TextView first_name,last_name;
    GoogleApiClient mGoogleApi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        first_name = (TextView) view.findViewById(R.id.firstName);
        last_name = (TextView) view.findViewById(R.id.lastName);

        loginButton = (LoginButton)view.findViewById(R.id.fbLoginBtn);

        loginButton.setFragment(this);
        loginButton.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestEmail().build();

        mGoogleApi = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .enableAutoManage((MainActivity)getActivity(),this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


        signInButton = (SignInButton)view.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button :
                signIn();
                break;

            case R.id.fbLoginBtn :
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"));

                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.i("Login FB Success","onSuccess");

                        progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Processsing your fb Login");

                        progressDialog.show();

                        String accessToken = loginResult.getAccessToken().getToken();
                        Log.i("Access Token Is::",accessToken);

                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("Graph Repsonse:",response.toString());

                                Bundle bFaceBookData= getFacebookData(object);
                                first_name.setText("First Name"+bFaceBookData.get("first_name"));
                                last_name.setText("Last Name"+bFaceBookData.get("last_name"));

                                // Lets create a method to read
                            }
                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields","id,first_name,last_name");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Log.e("fb : Login :Cancel","Login cancelled for fb");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.e("fb : Login :Cancel",error.toString());

                    }
                });
        }
    }

    private  Bundle getFacebookData(JSONObject object){
        Bundle bundle=null;
        try{
                progressDialog.dismiss();
                bundle = new Bundle();
                String id = object.getString("id");

            try{
                URL profile_pic = new URL("https://graph.facebook.com/"+id+"/picture?width=200&height=150");
                Log.i("Profile Pic Is :",profile_pic.toString());
                bundle.putString("profile_pic",profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            bundle.putString("idFacebook",id);

            if(object.has("first_name")){
                bundle.putString("first_name",object.getString("first_name"));
            }
            if(object.has("last_name")){
                bundle.putString("last_name",object.getString("last_name"));
            }
            if(object.has("email")){
                bundle.putString("email",object.getString("email"));
            }
            if(object.has("gender")){
                bundle.putString("gender",object.getString("gender"));
            }
            if(object.has("birthday")){
                bundle.putString("birthday",object.getString("birthday"));
            }
            if(object.has("location")){
                bundle.putString("location",object.getString("location"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  bundle;
    }
    private void signIn(){

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApi);
        startActivityForResult(signInIntent,1000);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1000){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                first_name.setText("Display Name ::"+account.getDisplayName());
                first_name.setText("Given Name ::"+account.getGivenName());

            }else{
                Log.e("Error Login","Error in Login"+result.toString());
            }
        }else{
            callbackManager.onActivityResult(1000,resultCode,data);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
            mGoogleApi.connect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mGoogleApi.disconnect();
    }
}
