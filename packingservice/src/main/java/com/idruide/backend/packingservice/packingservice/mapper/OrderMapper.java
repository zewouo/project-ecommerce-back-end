package com.idruide.backend.packingservice.packingservice.mapper;


import com.idruide.backend.packingservice.packingservice.dto.OrderDto;
import com.idruide.backend.packingservice.packingservice.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Mapper
public interface OrderMapper {

    @Named("toOrder")
    public Order toOrder(OrderDto orderDto);

    public OrderDto toOrderDto(Order order);
}
