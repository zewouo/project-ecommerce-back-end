package com.idruide.backend.packingservice.listener.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idruide.backend.packingservice.dto.OrderDto;
import com.idruide.backend.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.service.PackingService;
import com.idruide.backend.packingservice.utils.PackingXmlParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaPackingConsumer {

    @Autowired
    private PackingService packingService;

    @KafkaListener(topics = "topicdelivery")
    public void getMessage(String message) throws JsonProcessingException {
        OrderDto order = PackingXmlParser.getXmlMapper().readValue(message, OrderDto.class);
               log.info("Order receive from orderservice : " + order );
       PackingDto packingDto = PackingDto.builder().createdAt(order.getCreatedAt())
                .deliverDate(order.getDeliverDate())
                .codePacking(RandomStringUtils.randomAlphanumeric(8).toUpperCase())
                .comment("Automatic delivery")
                .orderNumber(order.getOrderNumber())
                .build();
        this.packingService.savePacking(packingDto);
        log.info("Created Packing with event from order N.: " + packingDto.getOrderNumber() + " in Packing service");


    }
}
