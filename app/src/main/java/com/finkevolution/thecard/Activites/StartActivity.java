package com.finkevolution.thecard.Activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.finkevolution.thecard.R;

/**
 * Created by alexander on 2017-06-28.
 */

public class StartActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();

        loginButton = (LoginButton)findViewById(R.id.login_button);

        setContentView(R.layout.activity_start);

        initilize();
        fbLogin();


    }

    private void checkLogin() {
        if(isLoggedIn()){
            startActivity(new Intent(StartActivity.this, MainActivity.class));

        }

    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    private void initilize() {

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

    }

    private void fbLogin() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        startActivity(new Intent(StartActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancel() {
                        Log.d("Login Failed" , "Cancelled Login");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("ERROR ON LOGIN ATEMPT"," Try again");
                    }
                });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

