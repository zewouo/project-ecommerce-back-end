package com.idruide.backend.catalogservice.catalogservice.service;


import com.idruide.backend.catalogservice.catalogservice.input.ProductInput;

import java.util.List;
/**
 *
 *
 * @author Thierry Kwekam
 */

public interface ProductService  {


        List<ProductInput> getAllProducts();

        ProductInput validateAndGetProductById(Integer id);

        ProductInput validateAndGetProductByName(String name);

        ProductInput saveProduct(ProductInput productInput);

        void deleteProduct(ProductInput productInput);

}
