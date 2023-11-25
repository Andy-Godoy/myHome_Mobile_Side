package com.example.myhome.Front;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.ByteArrayOutputStream;
import java.net.URI;

public class AgenciesProfile extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewProfile;
    private Button btnLogout;
    private RatingBar ratingBar;
    private Button btnDeleteAccount;
    private TextView textViewRatingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencies_profile);

        imageViewProfile = findViewById(R.id.imageViewProfile);



        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // mostramos mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }


        ratingBar = findViewById(R.id.ratingBar);
        textViewRatingValue = findViewById(R.id.textViewRatingValue);
        // aca podemos configurar otros atributos del RatingBar según sea necesario...
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
                Intent intent = new Intent(AgenciesProfile.this, AgenciesRating.class);
                startActivity(intent);
                return false;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(AgenciesProfile.this);
                builder.setTitle("Eliminar cuenta");
                builder.setMessage("¿Está seguro de que desea eliminar su cuenta?");
                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Eliminar la cuenta
                        // ...

                        // Mostramos un mensaje de confirmación de que la cuenta fue realmente eliminada.
                        AlertDialog.Builder builder = new AlertDialog.Builder(AgenciesProfile.this);
                        builder.setTitle("Cuenta eliminada");
                        builder.setMessage("Su cuenta se eliminó correctamente.");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // lo llevamos al activity LoginUser
                                Intent intent = new Intent(AgenciesProfile.this, LoginUser.class);
                                startActivity(intent);
                            }
                        });
                        builder.show();
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


      /*  Spinner spinnerCurrency = findViewById(R.id.spinnerCurrency);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.currency_options, // Definimos las opciones en strings.xml dentro del array
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapter); */


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





