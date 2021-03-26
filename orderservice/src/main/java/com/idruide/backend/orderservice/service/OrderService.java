package com.idruide.backend.orderservice.service;

import com.idruide.backend.orderservice.dto.OrderDto;

import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */

public interface OrderService {

    List<OrderDto> getAllOrders();

    OrderDto validateAndGetOrderById(Integer id);

    OrderDto saveOrder(OrderDto orderDto);

    OrderDto saveOrderProduct(OrderDto orderDto);


    void deleteOrder(OrderDto orderDto);


}
