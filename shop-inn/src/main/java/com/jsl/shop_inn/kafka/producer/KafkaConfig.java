package com.jsl.shop_inn.kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Value("${application.purchase.topic}")
    private String topic;

    @Bean
    public NewTopic purchaseTopic() {
        return TopicBuilder.name(topic).build();
    }
}
