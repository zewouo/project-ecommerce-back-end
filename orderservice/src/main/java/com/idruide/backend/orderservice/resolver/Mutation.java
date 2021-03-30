package com.idruide.backend.orderservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.service.OrderService;
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


    private OrderService orderService;

    @Autowired
    public Mutation(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Create Order with ID " + orderDto.getId() + " in Orderservice");
        return orderService.saveOrder(orderDto);
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
