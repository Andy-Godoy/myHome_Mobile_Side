package com.example.myhome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class UploadImageActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private AzureBlobStorageManager storageManager;
    private ImageView imageViewSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        storageManager = new AzureBlobStorageManager();
        imageViewSelected = findViewById(R.id.imageViewSelected);
        Button btnOpenGallery = findViewById(R.id.btnOpenGallery);

        btnOpenGallery.setOnClickListener(view -> openGallery());
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                // Obtener el InputStream de la imagen seleccionada
                InputStream inputStream = getContentResolver().openInputStream(uri);

                // Mostrar la imagen seleccionada en el ImageView
                imageViewSelected.setVisibility(View.VISIBLE);
                imageViewSelected.setImageURI(uri);

                // Subir la imagen al almacenamiento de blobs en Azure
                storageManager.uploadImage(inputStream, "imageName.jpg");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
