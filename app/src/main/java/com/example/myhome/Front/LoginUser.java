package com.example.myhome.Front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.model.GoogleCredentials;
import com.example.myhome.Api.MyHome;
import com.example.myhome.model.Users;
import com.example.myhome.Api.UsersApi;
import com.example.myhome.Interfaces.LoginCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginUser extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, LoginCallback {

    private GoogleApiClient googleApiClient;

    private SignInButton signInButton;

    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginuser);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        MyHome myHome = (MyHome) getApplication();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestServerAuthCode("387896738234-4ndcl2v0gpctt5nb087dr7btmm4ovvit.apps.googleusercontent.com") //toma el GoogleAuth_WebClient creado en https://console.cloud.google.com/apis/credentials/oauthclient/ (Como aplicacion web)

                .requestIdToken("387896738234-4ndcl2v0gpctt5nb087dr7btmm4ovvit.apps.googleusercontent.com") //toma el GoogleAuth_WebClient creado en https://console.cloud.google.com/apis/credentials/oauthclient/ (Como aplicacion web)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);

        signInButton.setSize(SignInButton.SIZE_WIDE);

        signInButton.setColorScheme(SignInButton.COLOR_DARK);

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen(result);
        } else {
            Toast.makeText(this, R.string.not_log_in, Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen(GoogleSignInResult result) {

        UsersApi usersApi = new UsersApi();
        GoogleCredentials credentials = new GoogleCredentials(
                result.getSignInAccount().getId(),
                result.getSignInAccount().getEmail(),
                result.getSignInAccount().getPhotoUrl().toString(),
                result.getSignInAccount().getDisplayName());

        Users user = usersApi.loginUsuario (credentials, this);


    }

    public void ingresar(View view) {
        Intent miIntent=new Intent(LoginUser.this, LoginAgencies.class);
        startActivity(miIntent);
    }

    // mostramos un mensaje Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLoginSuccess(Users user) {

        ((MyHome) this.getApplication()).setUsuario(user);

        if (user != null) {
            Intent intent = new Intent(LoginUser.this, ListUserProperties.class);
            startActivity(intent);
            finish(); // Finalizamos la actividad actual para evitar que el usuario regrese a ella con el botón "Atrás"
        }

       // Intent intent = new Intent(LoginUser.this, SecondActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
       // startActivity(intent);
    }

    @Override
    public void onLoginFailure(String errorMessage) {
        showToast(errorMessage);
    }

    @Override
    public void onUnregisterSuccess() {

    }


}

