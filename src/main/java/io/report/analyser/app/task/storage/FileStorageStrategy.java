package io.report.analyser.app.task.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageStrategy {
    String uploadReport(MultipartFile file) throws IOException;
}
