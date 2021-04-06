package com.idruide.backend.catalogservice.listener.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idruide.backend.catalogservice.dto.OrderDto;
import com.idruide.backend.catalogservice.service.ProductService;
import com.idruide.backend.catalogservice.utils.CatalogXmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaCatalogConsumer {

    @Autowired
    private ProductService productService;

    @KafkaListener(topics ="topicstock")
    public void getMessage(String message) throws JsonProcessingException {
        OrderDto order= CatalogXmlParser.getXmlMapper().readValue(message, OrderDto.class);
        productService.updateProduct(order.getProductIds());
        System.out.println(productService.updateProduct(order.getProductIds()));


    }
}
