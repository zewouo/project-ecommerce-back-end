package com.idruide.backend.catalogservice.service;


import com.idruide.backend.catalogservice.input.ProductInput;
import com.idruide.backend.catalogservice.exception.ProductNotFoundException;
import com.idruide.backend.catalogservice.mapper.ProductMapper;
import com.idruide.backend.catalogservice.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Service
public class ProductServiceImpl implements ProductService  {


    private ProductRepository productRepository ;


    private ProductMapper productMapper ;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<ProductInput> getAllProducts() {
        return this.productMapper.toProductsDto(this.productRepository.findAll());
    }

    @Override
    @Transactional
    public ProductInput validateAndGetProductById(Integer id) {
        return this.productMapper.toProductDto(this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found", id)));
    }

    @Override
    @Transactional
    public ProductInput validateAndGetProductByName(String name) {
        final String nameNormSpace = StringUtils.normalizeSpace(name);
        return this.productMapper.toProductDto(this.productRepository.findByNameIgnoreCase(nameNormSpace)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found", nameNormSpace)));
    }

    @Override
    @Transactional
    public ProductInput saveProduct(ProductInput productInput) {
        return this.productMapper.toProductDto(this.productRepository.save(this.productMapper.toProduct(productInput)));
    }

    @Override
    @Transactional
    public void deleteProduct(ProductInput productInput){
        this.productRepository.delete(this.productMapper.toProduct(productInput));

    }
}
