package com.idruide.backend.catalogservice.catalogservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import com.idruide.backend.catalogservice.catalogservice.input.ProductInput;
import com.idruide.backend.catalogservice.catalogservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private ProductService productService;


    public ProductInput createProduct(ProductInput productInput) {
        return productService.saveProduct(productInput);
    }

    public ProductInput updateProduct(ProductInput productInput) {
        return productService.saveProduct(productInput);
    }

    public ProductInput deleteProduct(Integer productId) {
        ProductInput productInput = productService.validateAndGetProductById(productId);
        productService.deleteProduct(productInput);
        return productInput;
    }

}
