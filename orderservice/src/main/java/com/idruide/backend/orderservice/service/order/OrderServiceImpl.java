package com.idruide.backend.orderservice.service.order;


import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.dto.OrderProductDto;
import com.idruide.backend.orderservice.entities.Order;
import com.idruide.backend.orderservice.entities.OrderProduct;
import com.idruide.backend.orderservice.entities.Product;
import com.idruide.backend.orderservice.exception.OrderNotFoundException;
import com.idruide.backend.orderservice.exception.ProductNotFoundException;
import com.idruide.backend.orderservice.mapper.OrderMapper;
import com.idruide.backend.orderservice.repository.OrderProductRepository;
import com.idruide.backend.orderservice.repository.OrderRepository;
import com.idruide.backend.orderservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.server.UID;
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
    public OrderDto getOrderByNumber(String numberOrder) {
        return  this.orderRepository.findByOrderNumber(numberOrder)
                .stream()
                .map(order->this.orderMapper.toOrderDto(order) )
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional
    public OrderDto saveOrder(OrderDto orderDto) {

        return Optional.ofNullable(orderDto)
                .filter(this::validateOrder)
                .map(orderdto ->this.orderMapper.toOrder(orderdto))
                .map(order ->  {
                    order.setOrderNumber(String.valueOf(new UID()));
                    return order;
                })
                .map( order -> this.orderRepository.save(order))
                .map(order -> this.orderMapper.toOrderDto(order))
                .orElse(null);



    }

    boolean validateOrder(OrderDto orderDto) {
        List <OrderProductDto> validated = Optional.ofNullable(orderDto.getOrderProducts())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(orderProduct -> {
                    Integer avalaibleQuantity = Optional.ofNullable(this.productRepository.findByCodeProduct(orderProduct.getCodeProduct()))
                 .orElse(Product.builder().quantity(0).build()).getQuantity();
                  if(avalaibleQuantity > orderProduct.getQuantity()){
                      return orderProduct;
                  }else {
                      return null;
                  }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return orderDto.getOrderProducts() != null && validated.containsAll(orderDto.getOrderProducts());

    }

    @Override
    @Transactional
    public OrderDto updateOrder(Integer orderId, Integer productId) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found", orderId));
        OrderProduct product = this.orderProductRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Products not found", productId));
        //order.addProducts(product);
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
        //get orderNumber
        String orderNumber = orderDto.getOrderNumber();
        //if not
        if(orderNumber==null){
            log.warn("order: not found. ");
            new OrderNotFoundException("Order: not found. ",-1);
        }
        //else if get order by number
        OrderDto orderDtoRec = getOrderByNumber(orderNumber);
        Order order = this.orderMapper.toOrder(orderDtoRec);
        this.orderRepository.delete(order);
    }

}
