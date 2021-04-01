package com.idruide.backend.catalogservice.service;


import com.idruide.backend.catalogservice.dto.ProductDto;
import com.idruide.backend.catalogservice.exception.ProductNotFoundException;
import com.idruide.backend.catalogservice.mapper.ProductMapper;
import com.idruide.backend.catalogservice.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<ProductDto> getAllProducts() {
        return this.productMapper.toProductsDto(this.productRepository.findAll());
    }

    @Override
    @Transactional
    public ProductDto validateAndGetProductById(Integer id) {
        return this.productMapper.toProductDto(this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found", id)));
    }

    @Override
    @Transactional
    public ProductDto validateAndGetProductByName(String name) {
        final String nameNormSpace = StringUtils.normalizeSpace(name);
        return this.productMapper.toProductDto(this.productRepository.findByNameIgnoreCase(nameNormSpace)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found", nameNormSpace)));
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {
        return this.productMapper.toProductDto(this.productRepository.save(this.productMapper.toProduct(productDto)));
    }


    @Override
    @Transactional
    public void deleteProduct(ProductDto productDto) {
        this.productRepository.delete(this.productMapper.toProduct(productDto));

    }

    @Override
    public ProductDto updateProduct(Integer id) {
        return null;
    }

    @Override
    public List<ProductDto> updateProduct(List<Integer> productIds) {
        return Optional.ofNullable(productIds)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(id -> this.productRepository.findById(id).orElse(null))
                .map(product -> {
                    product.setAvailable(Boolean.FALSE);
                    return product;
                })
                .map(product -> this.productRepository.save(product))
                .map(product -> this.productMapper.toProductDto(product) )
                .collect(Collectors.toList());
    }

}


