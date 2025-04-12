package io.report.analyser.app.task.service;


import io.report.analyser.app.task.model.EmailPayload;
import io.report.analyser.app.task.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendReportAnalysis(EmailPayload emailPayload) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailPayload.getEmail());
        message.setSubject(emailPayload.getSubject());
        message.setText(emailPayload.getBody());
        mailSender.send(message);
    }
}
