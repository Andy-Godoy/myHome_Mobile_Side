package com.example.myhome.Front;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.example.myhome.Api.AgencyApi;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.UsersApi;
import com.example.myhome.Interfaces.AgencyCallBack;
import com.example.myhome.Interfaces.LoginCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.Agencies;
import com.example.myhome.model.Users;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.ByteArrayOutputStream;
import java.net.URI;

public class UsersProfile extends AppCompatActivity implements LoginCallback {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewProfile;
    private Button btnLogout;
    private Button btnSaveUser;
    private Button btnDeleteAccount;
    private Users user;
    private EditText nombre;
    private EditText email;
    private Spinner spinnerCurrency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);

        imageViewProfile = findViewById(R.id.imageViewProfile);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // mostramos mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        //Recupero el usuario en contexto para tener los datos
        if (((MyHome) this.getApplication()).getUsuario() != null) {
            user = ((MyHome) this.getApplication()).getUsuario();
        }


        //Escucho si modificaron el selector de monedas y de ser así habilito el botón de guardado
        spinnerCurrency = findViewById(R.id.spinnerCurrency);
        if (spinnerCurrency.getOnItemSelectedListener() != null) {
            btnSaveUser.setEnabled(true);
        }


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

                        // Por ejemplo, si usamos SharedPreferences para almacenar el estado de inicio de sesión
                        SharedPreferences preferences = getSharedPreferences("mispreferencias", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();

                        //  lo llevamos al activity LoginUser
                        Intent intent = new Intent(UsersProfile.this, LoginUser.class);
                        startActivity(intent);
                        finish(); //  Finaliza la actividad actual
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


        btnSaveUser = findViewById(R.id.btnSaveUser);
        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(UsersProfile.this);
                builder.setTitle("Editar Usuario");
                builder.setMessage("¿Estás seguro que deseas editar tus datos?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int a) {

                        String text = spinnerCurrency.getSelectedItem().toString();

                        //Llamo a retrofit para editar el usuario
                        UsersApi usersApi = new UsersApi();
                        usersApi.editarUsuario(user, UsersProfile.this);
                        //
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int a) {
                        //  No hace nada
                        dialogInterface.dismiss();
                    }
                });

                // Mostrar el AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });





        imageViewProfile = findViewById(R.id.imageViewProfile);

        // Manejar el clic en el ImageView
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });


    }

        private void openGallery() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);
        }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Obtener la imagen seleccionada de la galería
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

                // Redondear la imagen
                setRoundedImage(bitmap);

                // Puedes agregar aquí la lógica para subir la imagen a Azure Blob Storage si lo necesitas
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void setRoundedImage(Bitmap bitmap) {
        // Crear un drawable redondeado
        RoundedBitmapDrawable circularDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        circularDrawable.setCircular(true);

        // Establecer la imagen redondeada en el ImageView
        imageViewProfile.setImageDrawable(circularDrawable);
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

            btnSaveUser.setEnabled(false);
            Toast.makeText(this, "Los cambios fueron realizados con éxito", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUnregisterSuccess() {
        Toast.makeText(this, "El usuario ha sido eliminado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UsersProfile.this, LoginUser.class);
        startActivity(intent);
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
        Intent volver=new Intent(UsersProfile.this, ListAgencieProperties.class);
        startActivity(volver);
    }


}





