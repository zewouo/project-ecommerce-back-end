package com.idruide.backend.orderservice.service;


import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.entities.OrderCo;
import com.idruide.backend.orderservice.mapper.OrderMapper;
import com.idruide.backend.orderservice.mapper.ProductMapper;
import com.idruide.backend.orderservice.repository.OrderRepository;
import com.idruide.backend.orderservice.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository ;

    @Autowired
    private OrderMapper orderMapper ;

    @Autowired
    private ProductMapper productMapper ;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderMapper.toOrdersDto(orderRepository.findAll());
    }

    @Override
    public OrderDto validateAndGetOrderById(Integer id) {
        return orderMapper.toOrderDto(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found", id)));
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        OrderCo orderCo = orderMapper.toOrder(orderDto);
        orderRepository.save(orderCo);
        return orderDto;
    }

    @Override
    public OrderDto saveOrderProduct(OrderDto orderDto) {
        OrderCo orderCo = orderMapper.toOrderProduct(orderDto);
        orderRepository.save(orderCo);
        return orderDto;
    }

    @Override
    public void deleteOrder(OrderDto orderDto) {
        orderRepository.delete(orderMapper.toOrderDelete(orderDto));
    }


}
