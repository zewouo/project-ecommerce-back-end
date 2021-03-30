package com.idruide.backend.orderservice.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(Query.class);

    private OrderService orderService;

    @Autowired
    public Query(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<OrderDto> getAllOrders() {
        log.info("get all Orders in Order service");
        return orderService.getAllOrders();
    }

    public OrderDto getOrderById(Integer orderId) {
        log.info("get Order By Id " + orderId);
        return orderService.validateAndGetOrderById(orderId);
    }

}
