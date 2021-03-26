package com.idruide.backend.orderservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.dto.ProductDto;
import com.idruide.backend.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private OrderService orderService;

    public OrderDto createOrder(OrderDto orderDto) { return orderService.saveOrder(orderDto); }

    public OrderDto updateOrder(OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    public OrderDto deleteOrder(Integer orderId) {
        OrderDto orderDto = orderService.validateAndGetOrderById(orderId);
        orderService.deleteOrder(orderDto);
        return orderDto;
    }

    public OrderDto addOrderProduct(Integer orderId, ProductDto productDto) {
        OrderDto orderDto = orderService.validateAndGetOrderById(orderId);
        List<ProductDto> listProducts  = orderDto.getProductDtos();
        if ((listProducts != null) && !listProducts.isEmpty())
       {
           listProducts.add(productDto);
        } else{
            listProducts = new ArrayList<>();
           listProducts.add(productDto);
           orderDto.setProductDtos(listProducts);
       }
        return orderService.saveOrderProduct(orderDto);
    }


}
