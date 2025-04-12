package io.report.analyser.app.task.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic reportTopic(){
        return new NewTopic("report-topic", 1, (short) 1);
    }
}
