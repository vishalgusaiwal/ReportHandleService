package io.report.analyser.app.task.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String useremail;

    @Column(nullable = false)
    private String filepath;

    @Column(nullable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @Column(nullable = false)
    private String status = "PENDING";

    public void SetUseremail(String useremail) {
        this.useremail = useremail;
    }
    public void SetFilepath(String filepath) {
        this.filepath = filepath;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public UUID getId() {
        return id;
    }
    public String GetEmail() {
        return this.useremail;
    }
}
