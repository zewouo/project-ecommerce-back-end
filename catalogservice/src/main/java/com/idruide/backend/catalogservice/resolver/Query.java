package com.idruide.backend.catalogservice.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import com.idruide.backend.catalogservice.dto.ProductInput;
import com.idruide.backend.catalogservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Component
@Slf4j
public class Query implements GraphQLQueryResolver {

    Logger logger = LoggerFactory.getLogger(Query.class);

    private ProductService productService;

    @Autowired
    public Query(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductInput> getAllProducts() {
        log.info("get all Products in catalog service");
        return productService.getAllProducts();
    }

    public ProductInput getProductById(Integer productId) {
        log.info("get Product with Id " + productId);
        return productService.validateAndGetProductById(productId);
    }

    public ProductInput getProductByName(String productName) {
        log.info("get Product with name " + productName);
        return productService.validateAndGetProductByName(productName);
    }

}
