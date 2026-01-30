package com.devpull.antifraudservice.kafka;

import com.devpull.transactionservice.domain.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class EventProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final NewTopic topic;

    public EventProducer(
            KafkaTemplate<String, byte[]> kafkaTemplate,
            ObjectMapper objectMapper,
            NewTopic topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.topic = topic;
    }

    public void sendMessage(Transaction transaction) {
        try {
            byte[] payload = objectMapper.writeValueAsBytes(transaction);
            kafkaTemplate.send(topic.name(), payload);
        } catch (Exception e) {
            log.error("Error serializing transaction event", e);
        }
    }

}
