package com.idruide.backend.catalogservice.listener.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idruide.backend.catalogservice.dto.OrderDto;
import com.idruide.backend.catalogservice.utils.CatalogXmlParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaCatalogConsumer {


    @KafkaListener(topics ="topicstock",groupId = "catalog_consumer")
    public void getMessage(String message) throws JsonProcessingException {
        System.out.println("******this is kafka message Thierry*******:");
        OrderDto order= CatalogXmlParser.getXmlMapper().readValue(message, OrderDto.class);

        System.out.println(order.getProductIds());

    }
}
