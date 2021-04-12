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

    OrderDto validateAndGetOrderById(Integer id);

    OrderDto saveOrder(OrderDto orderDto);

    OrderDto saveOrderProduct(OrderDto orderDto);

    OrderDto updateOrder(Integer orderId, Integer productId);


    void deleteOrder(OrderDto orderDto);


}
