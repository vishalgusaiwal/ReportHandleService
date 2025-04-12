package io.report.analyser.app.task.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.report.analyser.app.task.model.ReportMessage;
import io.report.analyser.app.task.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/reports")
public class FileHandler {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final FileUploadService fileUploadService;
    public FileHandler(FileUploadService uploadService, KafkaTemplate<String, String> kafkaTemplate) {
        this.fileUploadService = uploadService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadReport(@RequestParam("file") MultipartFile file,@RequestParam("email") String email) {
        try{
            String reportUrl = fileUploadService.upload(file);
            ReportMessage message = new ReportMessage(email, reportUrl);
            String KafkaMessage = new ObjectMapper().writeValueAsString(message);
            kafkaTemplate.send("report-topic", KafkaMessage);
            return ResponseEntity.ok("Report uploaded successfully"+KafkaMessage);
        }catch(IOException e){
            return ResponseEntity.internalServerError().body("Error saving report");
        }
    }
}
