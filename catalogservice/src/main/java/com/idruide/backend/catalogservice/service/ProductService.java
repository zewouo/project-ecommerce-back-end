package com.idruide.backend.catalogservice.service;


import com.idruide.backend.catalogservice.dto.ProductDto;

import java.util.List;
/**
 *
 *
 * @author Thierry Kwekam
 */

public interface ProductService  {


        List<ProductDto> getAllProducts();

        ProductDto validateAndGetProductById(Integer id);

        ProductDto validateAndGetProductByName(String name);

        ProductDto saveProduct(ProductDto productDto);

        void deleteProduct(ProductDto productDto);

        ProductDto updateProduct(Integer id);

       List<ProductDto> updateProduct(List<Integer> productIds);




}
