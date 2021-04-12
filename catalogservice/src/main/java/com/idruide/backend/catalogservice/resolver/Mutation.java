package com.idruide.backend.catalogservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.idruide.backend.catalogservice.dto.ProductDto;
import com.idruide.backend.catalogservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
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
        log.info("Create Product " + productDto.getName() + " in catalog service");
        return productService.saveProduct(productDto);
    }

    public ProductDto updateProduct(ProductDto productDto) {
        log.info("Update Product " + productDto.getName() + " in catalog service");
        return productService.updateProduct(productDto);
    }

    public ProductDto deleteProduct(String codeProduct) {
        log.info("Delete Product with code: " + codeProduct + " in catalog service");
        return this.productService.deleteProduct(codeProduct);
    }

}
