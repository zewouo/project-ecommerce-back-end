package com.idruide.backend.catalogservice.service;


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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private final ProductMapper productMapper;

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
        return  this.productMapper.toProductDto(product);
    }

    @Override
    @Transactional
    public ProductDto getProductByCode(String code) {
        final String codeProduct = StringUtils.normalizeSpace(code);
       Product prod = this.productRepository.findByCodeProduct(codeProduct)
                .stream()
                .findFirst()
                .orElse(null);
        return this.productMapper.toProductDto(prod);
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {

        return Optional.ofNullable(productDto.getCodeProduct())
                .filter(StringUtils::isNotBlank)
                .map(code -> this.productRepository.findByCodeProduct(code))
                .map (products ->
                        { if(products.isEmpty()) {
                                Product prod = this.productMapper.toProduct(productDto);
                                return new ArrayList<Product>() {{
                                    add(prod);
                                }};
                            } else return products;
                        }
                )
                .map(List::stream)
                .get()
                .map(product -> {
                    log.info("Update quantities of product: " + product);
                    product.setQuantity(product.getId()!= null ? Integer.sum(product.getQuantity(), productDto.getQuantity()):product.getQuantity() );
                    return product;
                })
                .map(product -> this.productRepository.save(product))
                .map(product -> this.productMapper.toProductDto(product))
                .findFirst().orElseThrow(() -> new ProductNotFoundException("code Product not found. ", -1));
    }

    @Override
    @Transactional

    public ProductDto deleteProduct(String codeProduct){
        //if not
        if(codeProduct==null){
            log.warn("Product code is not found. ");
        }
        //else check quantity if get product by code
        ProductDto productDtoRec = getProductByCode(codeProduct);
        //get quantity and compare
        if(productDtoRec!=null){
            this.productRepository.delete(this.productMapper.toProduct(productDtoRec));
            log.info("Product with code " + codeProduct + " deleted. ");
        }
        return productDtoRec;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto){
        //get codeProduct of productDto
        String codeProduct = productDto.getCodeProduct();
        //if not exist
       // else get product corresponding of codeProduct
        ProductDto productDtoRec = getProductByCode(codeProduct);
        //save
        return this.productMapper.toProductDto(this.productRepository.save(this.productMapper.toProduct(productDtoRec)));
    }

}


