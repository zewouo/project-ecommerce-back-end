package com.idruide.backend.packingservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idruide.backend.packingservice.dto.OrderDto;
import com.idruide.backend.packingservice.utils.PackingXmlParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaPackingConsumer {


    @KafkaListener(topics ="topicdelivery",groupId = "packing_consumer")
    public void getMessage(String message) throws JsonProcessingException {
        System.out.println("******this is kafka message Thierry*******:");
        OrderDto order= PackingXmlParser.getXmlMapper().readValue(message, OrderDto.class);

        System.out.println(order);

    }
}
