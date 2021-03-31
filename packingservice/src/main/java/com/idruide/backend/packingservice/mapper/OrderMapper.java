package com.idruide.backend.packingservice.mapper;


import com.idruide.backend.packingservice.dto.OrderDto;
import com.idruide.backend.packingservice.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


/**
 *
 *
 * @author Thierry Kwekam
 */
@Mapper
public interface OrderMapper {

    @Named("toOrder")
    @Mapping(source = "orderDto.createdAt", target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "orderDto.deliverDate", target = "deliverDate", dateFormat = "dd-MM-yyyy HH:mm")
    public Order toOrder(OrderDto orderDto);

    public OrderDto toOrderDto(Order order);
}
