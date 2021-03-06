package com.idruide.backend.catalogservice.mapper;


import com.idruide.backend.catalogservice.dto.ProductDto;
import com.idruide.backend.catalogservice.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Thierry Kwekam
 */

@Mapper
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    default List<ProductDto> toProductsDto(List<Product> products) {
        return Optional.ofNullable(products)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }
}
