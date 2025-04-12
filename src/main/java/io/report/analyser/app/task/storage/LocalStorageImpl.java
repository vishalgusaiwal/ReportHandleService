package io.report.analyser.app.task.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("localStorage")
public class LocalStorageImpl implements FileStorageStrategy{
    @Value("${file.upload-dir}")
    private String UploadDir;

    @Override
    public String uploadReport(MultipartFile file) throws IOException{
        String filePath = UploadDir + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        File targetFile = new File(filePath);
        file.transferTo(targetFile);
        return targetFile.getAbsolutePath();
    }
}
