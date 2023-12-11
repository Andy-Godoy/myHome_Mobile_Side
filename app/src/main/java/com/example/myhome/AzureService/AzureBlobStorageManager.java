package com.example.myhome.AzureService;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
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
    private List<String> uploadedImageUrls = new ArrayList<>();

    public List<String> getUploadedImageUrls() {
        return uploadedImageUrls;
    }

    private static final int PICK_IMAGE_REQUEST = 1;
    private List<Uri> selectedImages = new ArrayList<>();

    public List<Uri> getSelectedImages() {
        return selectedImages;
    }

    private BlobContainerClient blobContainerClient;
    private Context context;

    public AzureBlobStorageManager(Context context) {
        // Se colocan credenciales y URI de almacenamiento
        blobContainerClient = new BlobServiceClientBuilder()
                .endpoint("/*storageURL*/")
                .sasToken("/*sasToken*/")
                .buildClient().getBlobContainerClient("/*containerName*/");

        this.context = context;
    }

    public void openGallery(Intent data) {
        data.setType("image/*");
        data.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        data.setAction(Intent.ACTION_GET_CONTENT);
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

    public boolean uploadImages(ArrayList<Uri> imageUris) {
        selectedImages = imageUris;
        BlockingQueue<Uri> uploadQueue = new LinkedBlockingQueue<>(selectedImages);

        List<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < selectedImages.size(); i++) {
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Uri imageUri = uploadQueue.take();
                        String imageUrl = uploadImage(imageUri);
                        uploadedImageUrls.add(imageUrl);
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
            });
            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private String uploadImage(Uri imageUri) {
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

        // Devolver la URL del blob después de subir la imagen
        return getImageUrl(randomFileName); // Pasa el nombre del archivo generado
    }

    private String getImageUrl(String fileName) {
        return blobContainerClient.getBlobClient(fileName).getBlobUrl();
    }
}
