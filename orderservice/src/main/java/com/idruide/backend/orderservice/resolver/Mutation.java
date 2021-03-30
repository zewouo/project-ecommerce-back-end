package com.idruide.backend.orderservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(Mutation.class);

    private OrderService orderService;

    @Autowired
    public Mutation(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Create Order with ID " + orderDto.getId() + " in Order service");
        return orderService.saveOrder(orderDto);
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        log.info("Update Order with ID " + orderDto.getId() + " in Order service");
        return orderService.saveOrder(orderDto);
    }

    public OrderDto deleteOrder(Integer orderId) {
        OrderDto orderDto = orderService.validateAndGetOrderById(orderId);
        log.info("Delete Order with ID " + orderId + " in Order service");
        orderService.deleteOrder(orderDto);
        return orderDto;
    }

    public OrderDto addOrderProduct(Integer orderId, Integer productId) {
        log.info("Add product to  Order with orderId " + orderId + "and productId " + productId + " in Order service");
        return orderService.updateOrder(orderId, productId);
    }


}
