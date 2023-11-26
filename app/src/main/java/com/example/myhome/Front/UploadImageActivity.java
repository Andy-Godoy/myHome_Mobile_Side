package com.example.myhome.Front;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhome.AzureService.AzureBlobStorageManager;
import com.example.myhome.Network.NetworkUtils;
import com.example.myhome.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class UploadImageActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private AzureBlobStorageManager storageManager;
    private ImageView imageViewSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        // Validamos la conexión a Internet al iniciar la actividad que lo trae de la clase NetworkUtils.java
        if (NetworkUtils.isNetworkConnected(this)) {

        } else {
            // muestra mensaje de error si no hay conexión que lo trae de la clase NetworkUtils.java
            NetworkUtils.showNoInternetMessage(this);
        }

        storageManager = new AzureBlobStorageManager();
        imageViewSelected = findViewById(R.id.imageViewSelected);
        Button btnOpenGallery = findViewById(R.id.btnOpenGallery);

        btnOpenGallery.setOnClickListener(view -> openGallery());
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                // Obtenemos el InputStream de la imagen seleccionada
                InputStream inputStream = getContentResolver().openInputStream(uri);

                // Generamos un nombre único para la imagen
                String imageName = generateUniqueImageName();

                // Mostramos la imagen seleccionada en el ImageView
                imageViewSelected.setVisibility(View.VISIBLE);
                imageViewSelected.setImageURI(uri);

                // Subimos la imagen al almacenamiento de blobs en Azure
                storageManager.uploadImage(inputStream, imageName + ".jpg");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateUniqueImageName() {

        long timestamp = System.currentTimeMillis();


        String randomSuffix = String.valueOf(new Random().nextInt(100000));

       //aca concatenamos el nombre de la imagen con el timestamp y el random
        return "image_" + timestamp + "_" + randomSuffix;
    }
}
