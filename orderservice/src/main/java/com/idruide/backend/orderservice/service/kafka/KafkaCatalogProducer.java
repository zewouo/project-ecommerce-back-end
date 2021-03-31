package com.idruide.backend.orderservice.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaCatalogProducer {

    @Value("${idruide.kafka.order.producer}")
    private  String topicName;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void writeMessage(String msg){
        this.kafkaTemplate.send(topicName,msg);
    }
}
