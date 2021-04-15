package com.idruide.backend.orderservice.service.order;


import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.dto.OrderProductDto;
import com.idruide.backend.orderservice.entities.Order;
import com.idruide.backend.orderservice.entities.OrderProductPK;
import com.idruide.backend.orderservice.entities.Product;
import com.idruide.backend.orderservice.exception.OrderNotFoundException;
import com.idruide.backend.orderservice.mapper.OrderMapper;
import com.idruide.backend.orderservice.repository.OrderProductRepository;
import com.idruide.backend.orderservice.repository.OrderRepository;
import com.idruide.backend.orderservice.repository.ProductRepository;
import com.idruide.backend.orderservice.utils.OrderDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    private OrderRepository orderRepository;

    private OrderProductRepository orderProductRepository;

    private ProductRepository productRepository;

    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderProductRepository orderProductRepository,
                            ProductRepository productRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
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
    public OrderDto getOrderById(Integer id) {
        return this.orderMapper.toOrderDto(this.orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found", id)));
    }

    @Override
    @Transactional
    public OrderDto getOrderByNumber(String numberOrder) {
        return this.orderRepository.findByOrderNumber(numberOrder)
                .stream()
                .map(order -> this.orderMapper.toOrderDto(order))
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional
    public OrderDto saveOrder(OrderDto orderDto) {
        return Optional.ofNullable(orderDto)
                .filter(this::validateOrder)
                .map(orderTemp -> this.orderMapper.toOrder(orderTemp))
                .map(order -> {
                    String orderNumber = String.valueOf(new UID());
                    order.setOrderNumber(orderNumber);
                    order.setCreatedAt(OrderDateUtils.SystemDateNow());
                    order.setDeliverDate(OrderDateUtils.estimatedDeliveryDate());
                    order.getOrderProducts().forEach(orderProduct -> {
                        Product prod = this.productRepository.findByCodeProduct(orderProduct.getProductCode());
                        orderProduct.setProduct(prod);
                        orderProduct.setOrderNumber(orderNumber);
                        orderProduct.setId(OrderProductPK.builder().orderNumber(orderNumber).product(prod).build());
                        orderProduct.setOrderId(order);
                    });
                    return order;
                })
                .map(order -> this.orderRepository.save(order))
                .map(order -> this.orderMapper.toOrderDto(order))
                .orElse(null);
    }

    boolean validateOrder(OrderDto orderDto) {
        List<OrderProductDto> validated = Optional.ofNullable(orderDto.getOrderProducts())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(orderProduct -> {
                    Integer avalaibleQuantity = Optional.ofNullable(this.productRepository.findByCodeProduct(orderProduct.getCodeProduct()))
                            .orElse(Product.builder().quantity(0).build()).getQuantity();
                    if (avalaibleQuantity > orderProduct.getQuantity()) {
                        return orderProduct;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return orderDto.getOrderProducts() != null && validated.containsAll(orderDto.getOrderProducts());

    }

    public OrderDto deleteOrder(OrderDto orderDto) {
        return Optional.ofNullable(orderDto.getOrderNumber())
                .filter(StringUtils::isNotBlank)
                .map(ordNumber -> this.orderRepository.findByOrderNumber(ordNumber))
                .map(orders -> {
                    if (orders.isEmpty()) {
                        return null;
                    } else return orders;
                })
                .map(List::stream)
                .get()
                .map(order -> {
                    if (order != null) this.orderRepository.delete(order);
                    return this.orderMapper.toOrderDto(order);
                })
                .findFirst().orElseThrow(() -> new OrderNotFoundException("order Number not found. ", -1));
    }

    @Override
    @Transactional
    public OrderDto updateOrder(OrderDto orderDto) {
        return  Optional.ofNullable(orderDto.getOrderNumber())
                .filter(StringUtils::isNotBlank)
                .map(orderNumber -> orderRepository.findByOrderNumber(orderNumber))
                .map(orders -> {
                    if (orders.isEmpty()) {
                        Order ord = this.orderMapper.toOrder(orderDto);
                        return new ArrayList<Order>() {{
                            add(ord);
                        }};
                    } else return orders;
                })
                .map(List::stream)
                .get()
                .map(order -> {
                    order.setCreatedAt(OrderDateUtils.SystemDateNow());
                    order.setDeliverDate(OrderDateUtils.estimatedDeliveryDate());
                    order.getOrderProducts().forEach(orderProduct -> {
                        Product prod = this.productRepository.findByCodeProduct(orderProduct.getProductCode());
                        orderProduct.setProduct(prod);
                        orderProduct.setOrderNumber(orderDto.getOrderNumber());
                        orderProduct.setId(OrderProductPK.builder().orderNumber(orderDto.getOrderNumber()).product(prod).build());
                        orderProduct.setOrderId(order);
                    });
                    return order;
                })
                .map(order -> this.orderRepository.save(order))
                .map(order -> this.orderMapper.toOrderDto(order))
                .findFirst().orElseThrow(() -> new OrderNotFoundException("order not found. ", -1));
       }
}



