package com.example.myhome.Front;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.myhome.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class UploadImageActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private List<Uri> selectedImages = new ArrayList<>();
    private BlobContainerClient blobContainerClient;
    private TextView urlTextView;
    private GridView imageGridView;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        // Reemplaza con tus propias credenciales y URI de almacenamiento
        blobContainerClient = new BlobServiceClientBuilder()
                .endpoint("https://storagemyhome.blob.core.windows.net")
                .sasToken("si=full&sv=2022-11-02&sr=c&sig=dceTOJyNNUggF3oEYoEWkdhPRkpM8bmv3%2BNDIx5JS4o%3D")
                .buildClient().getBlobContainerClient("containermyhome");

        urlTextView = findViewById(R.id.urlTextView);
        imageGridView = findViewById(R.id.imageGridView);
        imageAdapter = new ImageAdapter((Context) this, (ArrayList<Uri>) selectedImages);
        imageGridView.setAdapter(imageAdapter);

        Button chooseImageButton = findViewById(R.id.chooseImageButton);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        Button uploadImageButton = findViewById(R.id.uploadImageButton);
        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImages();
            }
        });

        // Manejar clics en el GridView para mostrar la URL en el TextView
        imageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imageUrl = getImageUrl(position);
                urlTextView.setText("URL de la imagen: " + imageUrl);
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    addImageToSelectedList(imageUri);
                }
            } else {
                // Si no hay clipData, significa que se seleccionó una sola imagen
                Uri imageUri = data.getData();
                addImageToSelectedList(imageUri);
            }
        }
    }

    private void addImageToSelectedList(Uri imageUri) {
        selectedImages.add(imageUri);
        imageAdapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    private void uploadImages() {
        // Crear una cola de solicitudes para subir los archivos de forma asíncrona
        BlockingQueue<Uri> uploadQueue = new LinkedBlockingQueue<>(selectedImages);

        // Crear un hilo para cada subida de archivo
        for (int i = 0; i < selectedImages.size(); i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Recuperar la URI del archivo de la cola
                        Uri imageUri = uploadQueue.take();

                        // Subir el archivo a Azure Blob Storage
                        uploadImage(imageUri);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        // Si la cola está vacía, significa que se han subido todos los archivos
                        if (uploadQueue.isEmpty()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Mostrar un mensaje de éxito

                                    Toast.makeText(UploadImageActivity.this, "¡Imágenes subidas con éxito!", Toast.LENGTH_SHORT).show();

                                    // Limpiar la lista de imágenes seleccionadas
                                    selectedImages.clear();
                                    imageAdapter.notifyDataSetChanged();

                                }
                            });
                        }

                    }
                }
            }).start();
        }
    }

    private void uploadImage(Uri imageUri) {
        InputStream imageStream;
        Bitmap bitmap;
        try {
            imageStream = getContentResolver().openInputStream(imageUri);
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String randomFileName = UUID.randomUUID().toString() + ".jpg";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        try{
            blobContainerClient.getBlobClient(randomFileName).upload(new ByteArrayInputStream(byteArray), byteArray.length);
        }catch (Exception e){
            Log.e("uploadImage", "uploadImage: Algo falló pero sigue funcionando"  );
        }


        // Obtener la URL de la imagen después de subirla
        final String imageUrl = blobContainerClient.getBlobClient(randomFileName).getBlobUrl();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Actualizar el TextView con la URL de la imagen
                urlTextView.append("URL de la imagen: " + imageUrl+"\n");
            }
        });
    }


    private String getImageUrl(int position) {
        // Obtener la URL de la imagen en la posición dada
        String randomFileName = UUID.randomUUID().toString() + ".jpg";
        return blobContainerClient.getBlobClient(randomFileName).getBlobUrl();
    }


}
