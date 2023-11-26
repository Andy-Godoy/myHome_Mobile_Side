package com.example.myhome.AzureService;


import android.content.ClipData;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AzureBlobStorageManager {
    private static final int PICK_IMAGE_REQUEST = 1;
    private List<Uri> selectedImages = new ArrayList<>();
    public List<Uri> getSelectedImages() {
        return selectedImages;
    }
    private BlobContainerClient blobContainerClient;
    private Context context;

    public AzureBlobStorageManager(Context context) {
        // Reemplaza con tus propias credenciales y URI de almacenamiento
        blobContainerClient = new BlobServiceClientBuilder()
                .endpoint("https://storagemyhome.blob.core.windows.net")
                .sasToken("si=full&sv=2022-11-02&sr=c&sig=dceTOJyNNUggF3oEYoEWkdhPRkpM8bmv3%2BNDIx5JS4o%3D")
                .buildClient().getBlobContainerClient("containermyhome");

        this.context = context;
    }

    public void openGallery(Intent data) {
        data.setType("image/*");
        data.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        data.setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(NewProperties, PICK_IMAGE_REQUEST, -1,data);
        ClipData clipData = data.getClipData();
        if (clipData != null) {
            for (int i = 0; i < clipData.getItemCount(); i++) {
                Uri imageUri = clipData.getItemAt(i).getUri();
                addImageToSelectedList(imageUri);
            }
        } else {
            Uri imageUri = data.getData();
            addImageToSelectedList(imageUri);
        }
    }

    private void addImageToSelectedList(Uri imageUri) {
        selectedImages.add(imageUri);
    }

    public void uploadImages() {
        BlockingQueue<Uri> uploadQueue = new LinkedBlockingQueue<>(selectedImages);

        for (int i = 0; i < selectedImages.size(); i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Uri imageUri = uploadQueue.take();
                        uploadImage(imageUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (uploadQueue.isEmpty()) {
                            // Operación en el hilo principal
                            // Limpiar la lista de imágenes seleccionadas
                            selectedImages.clear();
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
            imageStream = context.getContentResolver().openInputStream(imageUri);
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String randomFileName = UUID.randomUUID().toString() + ".jpg";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        try {
            // Subir el archivo a Azure Blob Storage sin especificar la longitud del contenido
            blobContainerClient.getBlobClient(randomFileName).upload(new ByteArrayInputStream(byteArray), byteArray.length);
        } catch (Exception e) {
            Log.e("uploadImage", "uploadImage: Algo falló pero sigue funcionando");
        }
    }

    private String getImageUrl(int position) {
        String randomFileName = UUID.randomUUID().toString() + ".jpg";
        return blobContainerClient.getBlobClient(randomFileName).getBlobUrl();
    }
}
