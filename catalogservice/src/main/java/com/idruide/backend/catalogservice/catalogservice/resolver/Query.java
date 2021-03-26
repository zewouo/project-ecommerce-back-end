package com.idruide.backend.catalogservice.catalogservice.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import com.idruide.backend.catalogservice.catalogservice.input.ProductInput;
import com.idruide.backend.catalogservice.catalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private ProductService productService;


    public List<ProductInput> getAllProducts() {
        return productService.getAllProducts();
    }

    public ProductInput getProductById(Integer productId) {
        return productService.validateAndGetProductById(productId);
    }

    public ProductInput getProductByName(String productName) {
        return productService.validateAndGetProductByName(productName);
    }

}
