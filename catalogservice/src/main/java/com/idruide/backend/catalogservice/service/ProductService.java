package com.idruide.backend.catalogservice.service;


import com.idruide.backend.catalogservice.dto.OrderProductDto;
import com.idruide.backend.catalogservice.dto.ProductDto;

import java.util.List;

/**
 * @author Thierry Kwekam
 */

public interface ProductService {

        List<ProductDto> getAllProducts();

        ProductDto getProductByName(String name);

        ProductDto getProductByCode(String codeProduct);

        ProductDto saveProduct(ProductDto productDto);

        ProductDto deleteProduct(String codeProduct);

        ProductDto updateProduct(ProductDto productDto);

        ProductDto updateProductQuantity(OrderProductDto orderProductDto);

}
