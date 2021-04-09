package com.idruide.backend.orderservice.mapper;

import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.entities.Order;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Mapper(builder = @Builder(disableBuilder = true), uses = { OrderProductMapper.class})
public interface OrderMapper {


    @Mapping(source = "orderDto.createdAt", target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "orderDto.deliverDate", target = "deliverDate", dateFormat = "dd-MM-yyyy HH:mm")
    Order toOrder(OrderDto orderDto);

    Order toOrderProduct(OrderDto orderDto);

    Order toOrderDelete(OrderDto orderDto);

    @Mapping(source = "order.orderProducts", target = "orderProducts", qualifiedByName = "toOrderProductDtos")
    @Mapping(source = "order.createdAt", target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "order.deliverDate", target = "deliverDate", dateFormat = "dd-MM-yyyy HH:mm")
    OrderDto toOrderDto(Order order);


    default List<OrderDto> toOrdersDto(List<Order> orders) {
        return Optional.ofNullable(orders)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toOrderDto)
                .collect(Collectors.toList());

    }

}
