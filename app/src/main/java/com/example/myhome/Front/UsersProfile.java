package com.example.myhome.Front;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.UsersApi;
import com.example.myhome.Interfaces.LoginCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.Users;
import com.example.myhome.model.enums.CurrencyType;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.microsoft.azure.storage.blob.CloudBlockBlob;


import java.io.ByteArrayOutputStream;
import java.net.URI;

public class UsersProfile extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, LoginCallback {

    private boolean isUpdate = false;
    private  boolean isFirstTime = true;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewProfile;
    private Button btnLogout;

    private Button btnDeleteAccount;
    private Users user;
    private TextView nombre;
    private TextView email;
    private Spinner spinnerCurrency;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);

        imageViewProfile = findViewById(R.id.imageViewProfile);

        nombre = findViewById(R.id.textViewNameUser);
        email = findViewById(R.id.textViewEmailUser);
        spinnerCurrency = findViewById(R.id.spinnerCurrency);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();





        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // mostramos mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        //Recupero el usuario en contexto para tener los datos
        if (((MyHome) this.getApplication()).getUsuario() != null) {
            user = ((MyHome) this.getApplication()).getUsuario();
            nombre.setText(user.getUserName());
            email.setText(user.getUserEmail());
            spinnerCurrency.setSelection(((ArrayAdapter) spinnerCurrency.getAdapter()).getPosition(user.getUserCurrencyPreference().toString()));
            Glide.with(this).load(user.getUserImage()).into(imageViewProfile);
        }


        //Escucho si modificaron el selector de monedas y de ser así habilito el botón de guardado
        spinnerCurrency = findViewById(R.id.spinnerCurrency);
        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if(isFirstTime){
                    isFirstTime = false;
                    return;
                }
                user.setUserCurrencyPreference((spinnerCurrency.getSelectedItem().toString() == "USD") ? CurrencyType.USD : CurrencyType.ARS);
                UsersApi usersApi = new UsersApi();

                isUpdate = true;
                usersApi.editarUsuario(user, UsersProfile.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnLogout = findViewById(R.id.btnLogoutUser);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(UsersProfile.this);
                builder.setTitle("Cerrar Sesión");
                builder.setMessage("¿Estás seguro que desea cerrar sesión?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // aca podemos meter otras cosas que queramos que se hagan al cerrar sesion
                        revoke();

                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //  No hace nada
                        dialogInterface.dismiss();
                    }
                });

                // Mostrar el AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Mostramos un mensaje de advertencia al usuario
                AlertDialog.Builder builder = new AlertDialog.Builder(UsersProfile.this);
                builder.setTitle("Eliminar cuenta");
                builder.setMessage("¿Está seguro de que desea eliminar su cuenta?");
                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Llamo a retrofit para eliminar el usuario
                        UsersApi usersApi = new UsersApi();
                        usersApi.deleteUser(user.getUserId(), UsersProfile.this);
                        //
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // No hace nada
                    }
                });
                builder.show();
            }
        });


    }


    @Override
    public void onLoginFailure(String errorMessage) {

        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLoginSuccess(Users user) {
        if (user != null) {
            this.user = user;
            nombre.setText(user.getUserName());
            email.setText(user.getUserEmail());
           // spinnerCurrency.setSelection(((ArrayAdapter) spinnerCurrency.getAdapter()).getPosition(user.getUserCurrencyPreference()));
            if(isUpdate){
                Toast.makeText(this, "Los cambios fueron realizados con éxito", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onUnregisterSuccess() {
        Toast.makeText(this, "El usuario ha sido eliminado", Toast.LENGTH_SHORT).show();
        revoke();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private class UploadImageToAzureBlobStorageTask extends AsyncTask<Bitmap, Void, Void> {
        @Override
        protected Void doInBackground(Bitmap... bitmaps) {
            try {
                // Obtener una referencia al Blob Storage en Azure
                CloudBlockBlob blob = new CloudBlockBlob(new URI("https://storagemyhome.blob.core.windows.net/containermyhome/"));

                // Convertir la imagen a bytes
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmaps[0].compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] imageBytes = outputStream.toByteArray();

                // Subir la imagen al Blob Storage
                blob.uploadFromByteArray(imageBytes, 0, imageBytes.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public void volver(View view) {
        finish();
    }


    public void revoke() {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {

                    Intent intent = new Intent(UsersProfile.this, LoginUser.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.not_revoke, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}





