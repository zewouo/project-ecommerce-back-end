package com.idruide.backend.orderservice.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private  OrderService orderService;

    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    public OrderDto getOrderById(Integer orderId) {
        return orderService.validateAndGetOrderById(orderId);
    }




}
