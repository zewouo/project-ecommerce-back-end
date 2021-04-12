package com.idruide.backend.orderservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.service.kafka.KafkaCatalogProducer;
import com.idruide.backend.orderservice.service.kafka.KafkaPackingProducer;
import com.idruide.backend.orderservice.service.order.OrderService;
import com.idruide.backend.orderservice.utils.OrderXmlParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Component
@Slf4j
public class Mutation implements GraphQLMutationResolver {

    private KafkaPackingProducer packingProducer;

    private KafkaCatalogProducer catalogProducer;

    private OrderService orderService;

    @Autowired
    public Mutation(KafkaPackingProducer packingProducer,
                    KafkaCatalogProducer catalogProducer,
                    OrderService orderService) {
        this.packingProducer = packingProducer;
        this.catalogProducer = catalogProducer;
        this.orderService = orderService;
    }


    public OrderDto createOrder(OrderDto orderDto) throws JsonProcessingException {
        OrderDto order = orderService.saveOrder(orderDto);
       if(order!= null && order.getId()!= null){
            this.catalogProducer.publishToCatalog(OrderXmlParser.writeValueAsString(order));
            this.packingProducer.publishToPacking(OrderXmlParser.writeValueAsString(order));
            log.info(" Message send to catalog and packing " + OrderXmlParser.writeValueAsString(order));
        }

        return order;
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        if(orderDto.getOrderNumber()==null){
            log.warn("please enter a valid  number Order before Ubdate!!!");
        }
        log.info("Update Order with Order Number " + orderDto.getOrderNumber() + " in orderservice");
        return this.orderService.saveOrder(orderDto);
    }

    public OrderDto deleteOrder(OrderDto orderDto) {
        if(orderDto.getOrderNumber()==null){
            log.warn("please enter a valid  number Order before delete!!!");
        }
        log.info("Delete Order with  Order Number " + orderDto.getOrderNumber() + " in orderservice");
        return this.orderService.deleteOrder(orderDto);
    }


}
