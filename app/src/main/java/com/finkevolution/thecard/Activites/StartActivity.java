package com.finkevolution.thecard.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.finkevolution.thecard.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alexander on 2017-06-28.
 */

public class StartActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private CallbackManager callbackManager;
    private LoginButton facebookLoginBtn;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton googleLoginBtn;
    private String userID;


    private static final String TAG = "SignInActivity";
    private static final int Google_RC = 9001;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        facebookLoginBtn = (LoginButton)findViewById(R.id.facebook_login);
        googleLoginBtn = (SignInButton)findViewById(R.id.google_login);
        initiateGoogleLogin();
        initiateFacebookLogin();
        checkLogin();


        googleLoginBtn.setSize(SignInButton.SIZE_STANDARD);
        googleLoginBtn.setOnClickListener(new onGoogleClick());

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"Google Authentication has Failed please try again!", Toast.LENGTH_SHORT).show();
    }

    private void checkLogin() {
        Log.d("Checking Log in", isFacebookLoggedIn()  + " GOO" + isGoogleLoggedIn());
        if(isGoogleLoggedIn() || isFacebookLoggedIn()){
            Log.d("USER IS LOGGED IN LOLO", userID);
            startActivity(new Intent(StartActivity.this, MainActivity.class));
        }else{
            Log.d("LOLO FAILED", "FAIIL");
        }

    }

    public boolean isGoogleLoggedIn() {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone())
        {
            GoogleSignInResult result = opr.get();
            handleSignInResultForGoogle(result);
            return true;
        }else
            return false;
    }

    public boolean isFacebookLoggedIn(){
        boolean loggedIn = false;
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if (accessToken != null) {
            userID = accessToken.getUserId();
            loggedIn = true;

        } else {
            loggedIn=false;


        }
        return loggedIn;
    }


    private void initiateGoogleLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void initiateFacebookLogin(){

        callbackManager = CallbackManager.Factory.create();
        facebookLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                userID = loginResult.getAccessToken().getUserId();
                Log.d("FACEBOOK", "SUCCESS" + userID);
            }

            @Override
            public void onCancel() {
                return;
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplication(),"Facebook Login Failed, Please Try Again",Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Google_RC) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResultForGoogle(result);
            Log.d("Request Code Success", result.getStatus().getStatusMessage() + "");
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);

        checkLogin();
    }


    private void handleSignInResultForGoogle(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            userID = acct.getId();
        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this,"Google Login Failed", Toast.LENGTH_SHORT).show();
        }
    }




    private class onGoogleClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, Google_RC);
        }
    }

}
