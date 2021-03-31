package com.idruide.backend.catalogservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import com.idruide.backend.catalogservice.input.ProductInput;
import com.idruide.backend.catalogservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(Mutation.class);

    private ProductService productService;

    @Autowired
    public Mutation(ProductService productService) {
        this.productService = productService;
    }

    public ProductInput createProduct(ProductInput productInput) {
        log.info("create Product " + productInput.getName() + " in catalog service");
        return productService.saveProduct(productInput);
    }

    public ProductInput updateProduct(ProductInput productInput) {
        log.info("Update Product " + productInput.getName() + " in catalog service");
        return productService.saveProduct(productInput);
    }

    public ProductInput deleteProduct(Integer productId) {
        ProductInput productInput = productService.validateAndGetProductById(productId);
        log.info("Delete Product " + productInput.getName() + " in catalog service");
        productService.deleteProduct(productInput);
        return productInput;
    }

}
