package io.report.analyser.app.task.model;

public class ReportMessage {
    private String email;
    private String reportUrl;
    public ReportMessage(String email, String reportUrl) {
        this.email = email;
        this.reportUrl = reportUrl;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getReportUrl() {
        return reportUrl;
    }
    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }
}
