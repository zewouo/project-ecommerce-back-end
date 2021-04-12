package com.idruide.backend.catalogservice.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idruide.backend.catalogservice.dto.ProductDto;
import com.idruide.backend.catalogservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Thierry Kwekam
 */

@Component
@Slf4j
public class Query implements GraphQLQueryResolver {


    private final ProductService productService;

    @Autowired
    public Query(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductDto> getAllProducts() {
        log.info("get all Products in catalog service. ");
        return productService.getAllProducts();
    }

    public ProductDto getProductByCode(String codeProduct) {
        log.info("get Product with codeProduct: " + codeProduct);
        return productService.getProductByCode(codeProduct);
    }

    public ProductDto getProductByName(String productName) {
        log.info("get Product with name: " + productName);
        return productService.getProductByName(productName);
    }

}
