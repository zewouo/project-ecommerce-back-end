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

    default List<ProductDto> toProductsDto(List<Product> products){
        return Optional.ofNullable(products)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toProductDto)
                .collect(Collectors.toList());

    }
    @Named("toProducts")
    default List<Product> toProducts(List<ProductDto> productDtos){
        return Optional.ofNullable(productDtos)
                .map(List::stream).orElseGet(Stream::empty)
                .map(this::toProduct)
                .collect(Collectors.toList());

    }
}
