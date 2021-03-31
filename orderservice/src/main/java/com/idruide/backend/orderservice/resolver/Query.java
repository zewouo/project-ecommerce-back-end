package com.idruide.backend.orderservice.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Component
@Slf4j
public class Query implements GraphQLQueryResolver {


    private OrderService orderService;

    @Autowired
    public Query(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<OrderDto> getAllOrders() {
        log.info("Get all Orders in Orderservice");
        return orderService.getAllOrders();
    }

    public OrderDto getOrderById(Integer orderId) {
        log.info("Get Order By Id " + orderId);
        return orderService.validateAndGetOrderById(orderId);
    }

}
