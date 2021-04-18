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

    //save new  product if product non exist,else update quantity
    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {
        String code = productDto.getCodeProduct();
        if(code==null){
            log.info("code null..please enter code");
        }
        Product prod = this.productRepository.findByCodeProduct(code);
        if (prod == null) {
            prod = this.productMapper.toProduct(productDto);
            this.productRepository.save(prod);
            return productDto;
        }
        prod.setQuantity(prod.getId() != null ? Integer.sum(prod.getQuantity(), productDto.getQuantity()) : prod.getQuantity());
        return  this.productMapper.toProductDto(this.productRepository.save(prod));
    }

    //delete all product with this codeProduct
    @Override
    @Transactional
    public ProductDto deleteProduct(String codeProduct) {
        return Optional.ofNullable(codeProduct)
                .filter(StringUtils::isNotBlank)
                .map(code -> this.productRepository.findByCodeProduct(code))
                 .filter(obj -> true)
                .map(product -> {
                    if (product != null) this.productRepository.delete(product);
                    return this.productMapper.toProductDto(product);
                })
               .orElseThrow(() -> new ProductNotFoundException("code Product not found. ", -1));
    }

    //update all field of product but creating new product
    //with the same codeProduct
    @Override
    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        String code = productDto.getCodeProduct();
        if(code!=null){
            log.info("code null..please enter code");
        }
        Product product = this.productRepository.findByCodeProduct(code);
        if (product == null) {
            product = this.productMapper.toProduct(productDto);
            this.productRepository.save(product);
            return productDto;
        }
        product.setCodeProduct(productDto.getCodeProduct());
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return  this.productMapper.toProductDto(this.productRepository.save(product));
      }

      //update only quantity done by event
    @Override
    @Transactional
    public ProductDto updateProductQuantity(OrderProductDto orderProductDto) {
        return Optional.ofNullable(orderProductDto.getCodeProduct())
                .filter(StringUtils::isNotBlank)
                .map(code -> this.productRepository.findByCodeProduct(code))
                .filter(obj -> true)
                .map(product -> {
                    product.setQuantity((product.getQuantity() - orderProductDto.getQuantity()));
                    return product;
                })
                .map(product -> {
                    return  this.productRepository.save(product);
                })
                .map(this.productMapper::toProductDto)
                .orElseThrow(() -> new ProductNotFoundException("code Product not found. ", -1));
    }

}


