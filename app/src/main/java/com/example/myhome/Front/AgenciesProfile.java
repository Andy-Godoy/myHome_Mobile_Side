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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.ctc.wstx.util.StringUtil;
import com.example.myhome.Api.AgencyApi;
import com.example.myhome.Api.MyHome;
import com.example.myhome.Api.UsersApi;
import com.example.myhome.AzureService.AzureBlobStorageManager;
import com.example.myhome.Interfaces.AgencyCallBack;
import com.example.myhome.Interfaces.LoginCallback;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.example.myhome.model.Agencies;
import com.example.myhome.model.Users;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AgenciesProfile extends AppCompatActivity implements AgencyCallBack, LoginCallback {
    private static final int PICK_IMAGE_REQUEST = 1;
    private CircleImageView imageViewProfile;
    private Button btnLogout;
    private RatingBar ratingBar;
    private Button btnSaveAgency;
    private Button btnDeleteAccount;
    private TextView textViewRatingValue;
    private Users user;
    private Long agencyId;
    private Agencies agency;
    private EditText nombre;
    private EditText email;
    private AzureBlobStorageManager azureBlobStorageManager;
    private String imageUris;
    private ImageAdapter imageAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencies_profile);

        azureBlobStorageManager = new AzureBlobStorageManager(this);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {
        } else {
            // mostramos mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }
        //Recupero el usuario en contexto para tener los datos
        if (((MyHome) this.getApplication()).getUsuario() != null) {
            user = ((MyHome) this.getApplication()).getUsuario();
            agencyId = ((MyHome) this.getApplication()).getUsuario().getAgencyId();
        }


        ratingBar = findViewById(R.id.ratingBar);
        textViewRatingValue = findViewById(R.id.textViewRatingValue);

        // Agregamos un OnRatingBarChangeListener al RatingBar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Actualizar el TextView con el valor del RatingBar
                textViewRatingValue.setText(String.valueOf(rating));
            }
        });
        textViewRatingValue.setText(String.valueOf(ratingBar.getRating()));
        // Agregamos un OnTouchListener al RatingBar, porque esta desactivada la interaccion del click con el ratingbar
        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Iniciar la actividad AgenciesRating
                Intent intent = new Intent(AgenciesProfile.this, ListAgencieReviews.class);
                startActivity(intent);
                return false;
            }
        });

        //Escucho si modificaron el nombre y de ser así habilito el botón de guardado
        nombre = findViewById(R.id.textViewName);
        nombre.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSaveAgency.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Escucho si modificaron el email y de ser así habilito el botón de guardado
        email = findViewById(R.id.editTextEmail);
        email.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSaveAgency.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AgenciesProfile.this);
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
                        Intent intent = new Intent(AgenciesProfile.this, LoginUser.class);
                        startActivity(intent);
                        finish(); //  Finaliza la actividad actual
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
                AlertDialog.Builder builder = new AlertDialog.Builder(AgenciesProfile.this);
                builder.setTitle("Eliminar cuenta");
                builder.setMessage("¿Está seguro de que desea eliminar su cuenta?");
                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Llamo a retrofit para eliminar el usuario
                        UsersApi usersApi = new UsersApi();
                        usersApi.deleteUser(user.getUserId(), AgenciesProfile.this);
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

        btnSaveAgency = findViewById(R.id.btnSaveAgency);
        btnSaveAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AgenciesProfile.this);
                builder.setTitle("Editar Agencia");
                builder.setMessage("¿Estás seguro que deseas editar tus datos?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int a) {
                        agency.setAgencyName(nombre.getText().toString());
                        agency.setAgencyEmail(email.getText().toString());
                        ArrayList<Uri> image = new ArrayList<>();
                        image.add(Uri.parse(imageUris));

                        if (azureBlobStorageManager.uploadImages(image)){

                            Object[] objectArray = azureBlobStorageManager.getUploadedImageUrls().toArray();
                            String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);

                            agency.setAgencyImage(stringArray[0]);

                        }

                        //Llamo a retrofit para
                        AgencyApi agencyApi = new AgencyApi();
                        agencyApi.editarAgencia(agency, AgenciesProfile.this);
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
                btnSaveAgency.setEnabled(true);
                openGallery();
            }
        });
        AgencyApi agencyApi = new AgencyApi();
        agency = agencyApi.getAgency(agencyId, this);
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
            imageUris = data.getData().toString();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(imageUris));

                // Redondear la imagen
                setRoundedImage(bitmap);

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
    public void onLoginSuccess(Users user) {

    }
    @Override
    public void onLoginFailure(String errorMessage) {

        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onFailure(String errorMessage) {

    }
    @Override
    public void onAgencySuccess(Agencies agency, Boolean isUpdate) {
        if (agency != null) {
            this.agency = agency;
            nombre.setText(agency.getAgencyName());
            email.setText(agency.getAgencyEmail());
            btnSaveAgency.setEnabled(false);

            if (agency.getAgencyRating() != null) {
                //Cargo los datos del reseñas
                ratingBar.setRating(agency.getAgencyRating());
            }
            if (isUpdate){
                Toast.makeText(this, "Los cambios fueron realizados con éxito", Toast.LENGTH_SHORT).show();
            }

            if(agency.getAgencyImage() != null && agency.getAgencyImage() != ""){
                imageUris = agency.getAgencyImage();
                Picasso.get().load(agency.getAgencyImage()).into(imageViewProfile);
            } else {
                imageUris = "";
                Picasso.get().load("https://storagemyhome.blob.core.windows.net/containermyhome/nodisponible.jpg");
            }
        }
    }

    @Override
    public void onUnregisterSuccess() {
        Toast.makeText(this, "El usuario ha sido eliminado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AgenciesProfile.this, LoginUser.class);
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
        Intent volver=new Intent(AgenciesProfile.this, ListAgencieProperties.class);
        startActivity(volver);
    }

}





