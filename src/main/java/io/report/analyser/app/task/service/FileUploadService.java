package io.report.analyser.app.task.service;


import io.report.analyser.app.task.storage.FileStorageStrategy;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadService {
    private final FileStorageStrategy fileStorageStrategy;
    @Autowired
    public FileUploadService(@Qualifier("blobStorage") FileStorageStrategy fileStorageStrategy) {
        this.fileStorageStrategy = fileStorageStrategy;
    }
    public String upload(MultipartFile file) throws IOException {
        return fileStorageStrategy.uploadReport(file);
    }
}
