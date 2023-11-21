package com.example.myhome.AzureService;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import java.io.IOException;
import java.io.InputStream;

public class AzureBlobStorageManager {
    private static final String accountName = "storagemyhome";
    private static final String accountKey = "bl78iMke3Ssx98g45WS2mpx317rnslkgtwgprf6NfyzqSGr8HVmgSoGWfTfY7whuVfSTSsupe/9S+AStgIgPEA==";
    private static final String containerName = "containermyhome";

    private BlobContainerClient containerClient;

    public AzureBlobStorageManager() {
        String connectionString = String.format("DefaultEndpointsProtocol=https;AccountName=storagemyhome;AccountKey=bl78iMke3Ssx98g45WS2mpx317rnslkgtwgprf6NfyzqSGr8HVmgSoGWfTfY7whuVfSTSsupe/9S+AStgIgPEA==;EndpointSuffix=core.windows.net", accountName, accountKey);

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    // Método para subir una imagen al almacenamiento de blobs
    public void uploadImage(InputStream imageStream, String imageName) throws IOException {
        containerClient.getBlobClient(imageName).upload(imageStream, imageStream.available(), true);
    }
}