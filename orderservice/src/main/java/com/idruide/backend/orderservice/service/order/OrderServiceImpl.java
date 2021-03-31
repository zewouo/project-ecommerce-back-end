package com.idruide.backend.orderservice.service.order;


import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.entities.Order;
import com.idruide.backend.orderservice.entities.Product;
import com.idruide.backend.orderservice.exception.OrderNotFoundException;
import com.idruide.backend.orderservice.exception.ProductNotFoundException;
import com.idruide.backend.orderservice.mapper.OrderMapper;
import com.idruide.backend.orderservice.repository.OrderRepository;
import com.idruide.backend.orderservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Service
public class OrderServiceImpl implements OrderService {


    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;

    }

    @Override
    @Transactional
    public List<OrderDto> getAllOrders() {
        return this.orderMapper.toOrdersDto(this.orderRepository.findAll());
    }

    @Override
    @Transactional
    public OrderDto validateAndGetOrderById(Integer id) {
        return this.orderMapper.toOrderDto(this.orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found", id)));
    }

    @Override
    @Transactional
    public OrderDto saveOrder(OrderDto orderDto) {
        List<Product> products = this.productRepository.retrieveProductsByIds(Optional.ofNullable(orderDto.getProductIds()).orElseThrow(() ->
                new ProductNotFoundException("product not found")));
        Order order = this.orderMapper.toOrder(orderDto);
        order.setProducts(products);
        return this.orderMapper.toOrderDto(this.orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderDto updateOrder(Integer orderId, Integer productId) {

        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found", orderId));
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Products not found", productId));
        order.addProducts(product);
        return this.orderMapper.toOrderDto(this.orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderDto saveOrderProduct(OrderDto orderDto) {
        Order order = this.orderMapper.toOrderProduct(orderDto);
        this.orderRepository.save(order);
        return orderDto;
    }

    @Override
    @Transactional
    public void deleteOrder(OrderDto orderDto) {
        this.orderRepository.delete(this.orderMapper.toOrderDelete(orderDto));
    }

}
