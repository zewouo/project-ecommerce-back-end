package com.idruide.backend.catalogservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.idruide.backend.catalogservice.dto.ProductDto;
import com.idruide.backend.catalogservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Component
@Slf4j
public class Mutation implements GraphQLMutationResolver {

    private ProductService productService;

    @Autowired
    public Mutation(ProductService productService) {
        this.productService = productService;
    }

    public ProductDto createProduct(ProductDto productDto) {
        log.info("create Product " + productDto.getName() + " in catalog service");
        return productService.saveProduct(productDto);
    }

    public ProductDto updateProduct(ProductDto productDto) {
        log.info("Update Product " + productDto.getName() + " in catalog service");
        return productService.saveProduct(productDto);
    }

    public ProductDto deleteProduct(Integer productId) {
        ProductDto productDto = productService.validateAndGetProductById(productId);
        log.info("Delete Product " + productDto.getName() + " in catalog service");
        productService.deleteProduct(productDto);
        return productDto;
    }

}
