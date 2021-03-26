package com.idruide.backend.catalogservice.catalogservice.service;


import com.idruide.backend.catalogservice.catalogservice.input.ProductInput;
import com.idruide.backend.catalogservice.catalogservice.exception.ProductNotFoundException;
import com.idruide.backend.catalogservice.catalogservice.mapper.ProductMapper;
import com.idruide.backend.catalogservice.catalogservice.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Service
public class ProductServiceImpl implements ProductService  {

    @Autowired
    private ProductRepository productRepository ;

    @Autowired
    private ProductMapper productMapper ;

    @Override
    public List<ProductInput> getAllProducts() {
        return productMapper.toProductsDto(productRepository.findAll());
    }

    @Override
    public ProductInput validateAndGetProductById(Integer id) {
        return productMapper.toProductDto(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found", id)));
    }

    @Override
    public ProductInput validateAndGetProductByName(String name) {
        final String nameNormSpace = StringUtils.normalizeSpace(name);
        return productMapper.toProductDto(productRepository.findByNameIgnoreCase(nameNormSpace)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found", nameNormSpace)));
    }

    @Override
    public ProductInput saveProduct(ProductInput productInput) {
        return productMapper.toProductDto(productRepository.save(productMapper.toProduct(productInput)));
    }

    @Override
    public void deleteProduct(ProductInput productInput){
        productRepository.delete(productMapper.toProduct(productInput));

    }
}
