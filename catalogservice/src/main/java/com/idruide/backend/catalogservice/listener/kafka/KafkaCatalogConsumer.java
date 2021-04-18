package com.idruide.backend.catalogservice.listener.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idruide.backend.catalogservice.dto.OrderDto;
import com.idruide.backend.catalogservice.dto.OrderProductDto;
import com.idruide.backend.catalogservice.mapper.ProductMapper;
import com.idruide.backend.catalogservice.repository.ProductRepository;
import com.idruide.backend.catalogservice.service.ProductService;
import com.idruide.backend.catalogservice.utils.CatalogXmlParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaCatalogConsumer {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @KafkaListener(topics = "topicstock")
    public void getMessage(String message) throws JsonProcessingException {
        OrderDto orderDto = CatalogXmlParser.getXmlMapper().readValue(message, OrderDto.class);
        log.info("Order receive from orderservice : " + orderDto );
        for (OrderProductDto order : orderDto.getOrderProducts()) {
            this.productService.updateProductQuantity(order);
            log.info("UpdateProductQuantity  for codeProduct: " + order.getCodeProduct());
        }
    }
}
