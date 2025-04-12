package io.report.analyser.app.task.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service("blobStorage")
public class BlobStorageImpl implements FileStorageStrategy{
    private final BlobContainerClient blobContainerClient;
    public BlobStorageImpl(@Value("${azure.storage.connection-string}") String ConnectionString, @Value("${azure.storage.container-name}") String ContainerName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(ConnectionString).buildClient();
        this.blobContainerClient = blobServiceClient.getBlobContainerClient(ContainerName);
        if(!this.blobContainerClient.exists()) {
            this.blobContainerClient.create();
        }
    }
    public String uploadReport(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() +"-"+file.getOriginalFilename();
        BlobClient blobClient = this.blobContainerClient.getBlobClient(fileName);
        blobClient.upload(file.getInputStream(),file.getSize(),true);
        return blobClient.getBlobUrl();
    }
}
