package com.jsl.shop_inn.kafka.consumer.service;

import com.jsl.shop_inn.common.util.PurchaseMessage;
import com.jsl.shop_inn.kafka.consumer.email.ConsumerEmailService;
import com.jsl.shop_inn.kafka.consumer.email.EmailTemplateName;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final ConsumerEmailService emailService;

    @KafkaListener(topics = "processing", groupId = "process-order")
    public void consume(PurchaseMessage purchaseMessage) throws MessagingException {
        emailService.sendProcessEmail(
                purchaseMessage.getUsername(), purchaseMessage.getFullName(), EmailTemplateName.PROCESSING, purchaseMessage, "Processing In Progress"
        );
    }
}
