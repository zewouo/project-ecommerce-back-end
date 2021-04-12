package com.idruide.backend.orderservice.service.order;

import com.idruide.backend.orderservice.dto.OrderDto;

import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */

public interface OrderService {

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Integer id);

    OrderDto saveOrder(OrderDto orderDto);

    OrderDto getOrderByNumber(String numberOrder);

    OrderDto updateOrder(OrderDto orderDto);

    OrderDto deleteOrder(OrderDto orderDto);


}
