package com.idruide.backend.orderservice.mapper;

import com.idruide.backend.orderservice.dto.OrderDto;
import com.idruide.backend.orderservice.entities.OrderCo;
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

@Mapper(builder = @Builder(disableBuilder = true),
        uses = { ProductMapper.class
        })
public interface OrderMapper {

    @Mapping(source ="orderDto.productDtos", target = "products", qualifiedByName="toProducts")
    @Mapping(source = "orderDto.createdAt", target = "createdAt", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "orderDto.deliverDate", target = "deliverDate", dateFormat = "dd-MM-yyyy")
    public OrderCo toOrder(OrderDto orderDto);

    public OrderCo toOrderProduct(OrderDto orderDto);

    public OrderCo toOrderDelete(OrderDto orderDto);

    public OrderDto toOrderDto(OrderCo orderCo);

    default List<OrderDto> toOrdersDto(List<OrderCo> orderCos){
        return Optional.ofNullable(orderCos)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toOrderDto)
                .collect(Collectors.toList());

    }

}
