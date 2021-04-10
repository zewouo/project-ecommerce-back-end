package com.idruide.backend.orderservice.mapper;


import com.idruide.backend.orderservice.dto.OrderProductDto;
import com.idruide.backend.orderservice.entities.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Mapper
public interface OrderProductMapper {
    @Mapping(source = "codeProduct", target = "productCode")
    OrderProduct toOrderProduct(OrderProductDto orderProductDto);
    @Mapping(source = "product.product.codeProduct", target = "codeProduct")
    OrderProductDto toProductDto(OrderProduct product);

    @Named("toOrderProductDtos")
    default List<OrderProductDto> toOrderProductDtos(List<OrderProduct> orderProducts) {
        return Optional.ofNullable(orderProducts)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    @Named("toOrderProducts")
    default List<OrderProduct> toOrderProducts(List<OrderProductDto> orderProductDtos) {
        return Optional.ofNullable(orderProductDtos)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toOrderProduct)
                .collect(Collectors.toList());

    }
}

