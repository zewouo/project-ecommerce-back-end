package com.idruide.backend.orderservice.mapper;


import com.idruide.backend.orderservice.dto.ProductDto;
import com.idruide.backend.orderservice.entities.Product;
import org.mapstruct.Mapper;
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
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    @Named("toProductIds")
    default List<Integer> toProductIds(List<Product> products) {
        return Optional.ofNullable(products)
                .map(List::stream).orElseGet(Stream::empty)
                .map(Product::getId)
                .collect(Collectors.toList());

    }

    @Named("toProducts")
    default List<Product> toProducts(List<ProductDto> productDtos) {
        return Optional.ofNullable(productDtos)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toProduct)
                .collect(Collectors.toList());

    }
}
