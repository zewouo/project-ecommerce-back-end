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

        ProductDto getProductByName(String name);

        ProductDto getProductByCode(String codeProduct);

        ProductDto saveProduct(ProductDto productDto);

        void deleteProduct(ProductDto productDto);

        ProductDto updateProduct(ProductDto productDto);


}
