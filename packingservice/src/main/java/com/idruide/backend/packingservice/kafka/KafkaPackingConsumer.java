package com.idruide.backend.packingservice.kafka;

import com.idruide.backend.packingservice.service.PackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaPackingConsumer {

    @Autowired
    private PackingService packingService;

/*    @KafkaListener(topics ="topicdelivery")
    public void getMessage(String message) throws JsonProcessingException {

        OrderDto order = PackingXmlParser.getXmlMapper().readValue(message, OrderDto.class);
        PackingDto packingDto =  PackingDto.builder().createdAt(order.getCreatedAt())
                .deliverDate(order.getDeliverDate())
                .code("GGAAA")
                .comment("Automatic delivery")
                .orderId(order.getId())
                .build();
        this.packingService.savePacking(packingDto);
        log.info("Created Packing from order N.: " + packingDto.getOrderId() + " in Packing service");

    }*/
}
