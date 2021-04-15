package com.idruide.backend.catalogservice.service;


import com.idruide.backend.catalogservice.dto.OrderProductDto;
import com.idruide.backend.catalogservice.dto.ProductDto;
import com.idruide.backend.catalogservice.entities.Product;
import com.idruide.backend.catalogservice.exception.ProductNotFoundException;
import com.idruide.backend.catalogservice.mapper.ProductMapper;
import com.idruide.backend.catalogservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Thierry Kwekam
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private ProductRepository productRepository;

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
    public ProductDto getProductByName(String name) {
        Product product = this.productRepository.findByName(name)
                .stream()
                .findFirst()
                .orElse(null);
        return this.productMapper.toProductDto(product);
    }

    @Override
    @Transactional
    public ProductDto getProductByCode(String code) {
        final String codeProduct = StringUtils.normalizeSpace(code);
        Product prod = this.productRepository.findByCodeProduct(codeProduct);
        return this.productMapper.toProductDto(prod);
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {
        return Optional.ofNullable(productDto.getCodeProduct())
                .filter(StringUtils::isNotBlank)
                .map(code -> this.productRepository.findByCodeProduct(code))
                .map(product ->
                        {
                            if (product==null) {
                                Product prod = this.productMapper.toProduct(productDto);
                                    return prod;

                            } else return product;
                        }
                )
                .map(product -> {
                    log.info("Add quantities of product: " + product);
                    product.setQuantity(product.getId() != null ? Integer.sum(product.getQuantity(), productDto.getQuantity()) : product.getQuantity());
                    return product;
                })
                .map(product -> this.productRepository.save(product))
                .map(product -> this.productMapper.toProductDto(product))
                .orElseThrow(() -> new ProductNotFoundException("code Product not found. ", -1));
    }

    public ProductDto deleteProduct(String codeProduct) {
        return Optional.ofNullable(codeProduct)
                .filter(StringUtils::isNotBlank)
                .map(code -> this.productRepository.findByCodeProduct(code))
                 .filter(Objects::nonNull)
                .map(product -> {
                    if (product != null) this.productRepository.delete(product);
                    return this.productMapper.toProductDto(product);
                })
               .orElseThrow(() -> new ProductNotFoundException("code Product not found. ", -1));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        return Optional.ofNullable(productDto.getCodeProduct())
                .filter(StringUtils::isNotBlank)
                .map(code -> this.productRepository.findByCodeProduct(code))
                .filter(Objects::nonNull)
                .map(product -> {
                    product.setCodeProduct(productDto.getCodeProduct());
                    product.setDescription(productDto.getDescription());
                    product.setName(productDto.getName());
                    product.setPrice(productDto.getPrice());
                    product.setQuantity(productDto.getQuantity());
                    return product;
                })
                .map(product -> {
                    log.info(" product : " + product);
                    return  this.productRepository.save(product);
                })
                .map(product -> this.productMapper.toProductDto(product))
                .orElseThrow(() -> new ProductNotFoundException("code Product not found. ", -1));
    }

    @Override
    @Transactional
    public ProductDto updateProductQuantity(OrderProductDto orderProductDto) {
        return Optional.ofNullable(orderProductDto.getCodeProduct())
                .filter(StringUtils::isNotBlank)
                .map(code -> this.productRepository.findByCodeProduct(code))
                .filter(Objects::nonNull)
                .map(product -> {
                    product.setQuantity((product.getQuantity() - orderProductDto.getQuantity()));
                    return product;
                })
                .map(product -> {
                    return  this.productRepository.save(product);
                })
                .map(product -> this.productMapper.toProductDto(product))
                .orElseThrow(() -> new ProductNotFoundException("code Product not found. ", -1));
    }

}


