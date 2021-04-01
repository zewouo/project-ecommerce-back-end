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
        log.info("Create Order with ID " + order.getId() + " in Orderservice");

        if(order!= null && order.getId()!= null){
            this.catalogProducer.writeMessage(OrderXmlParser.writeValueAsString(order));
            this.packingProducer.writeMessage(OrderXmlParser.writeValueAsString(order));
            System.out.println(OrderXmlParser.writeValueAsString(order));
        }

        return order;
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        log.info("Update Order with ID " + orderDto.getId() + " in orderservice");
        return orderService.saveOrder(orderDto);
    }

    public OrderDto deleteOrder(Integer orderId) {
        OrderDto orderDto = orderService.validateAndGetOrderById(orderId);
        log.info("Delete Order with ID " + orderId + " in orderservice");
        orderService.deleteOrder(orderDto);
        return orderDto;
    }

    public OrderDto addOrderProduct(Integer orderId, Integer productId) {
        log.info("Add product to  Order with orderId " + orderId + "and productId " + productId + " in orderservice");
        return orderService.updateOrder(orderId, productId);
    }


}
