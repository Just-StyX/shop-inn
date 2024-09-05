package com.jsl.shop_inn.kafka.producer;

import com.jsl.shop_inn.common.util.PurchaseMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final NewTopic topic;
    private final KafkaTemplate<String, PurchaseMessage> kafkaTemplate;

    public void sendProcessingMessage(PurchaseMessage purchaseMessage) {
        Message<PurchaseMessage> message = MessageBuilder
                .withPayload(purchaseMessage)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
        kafkaTemplate.send(message);
    }
}
