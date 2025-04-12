package io.report.analyser.app.task.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.report.analyser.app.task.model.EmailPayload;
import io.report.analyser.app.task.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import io.report.analyser.app.task.model.Report;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReportConsumer {
    @Autowired
    private EmailService emailService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics =  "email-output-topic",groupId = "email-send-group")
    public void processReport(String message){
        try{
            EmailPayload emailPayload = objectMapper.readValue(message,EmailPayload.class);
            emailService.sendReportAnalysis(emailPayload);
        }catch (JsonMappingException e) {
            System.err.println("❌ JSON Mapping error: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.err.println("❌ JSON Processing error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ General error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
