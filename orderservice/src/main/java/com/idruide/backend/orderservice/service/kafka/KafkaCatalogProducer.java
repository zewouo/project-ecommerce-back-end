package com.idruide.backend.orderservice.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaCatalogProducer {

    @Value("${idruide.kafka.order.producer}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishToCatalog(String message) {
        this.kafkaTemplate.send(topicName, message);
    }
}
