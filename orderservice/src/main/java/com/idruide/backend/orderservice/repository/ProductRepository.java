package com.idruide.backend.orderservice.repository;

import com.idruide.backend.orderservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thierry Kwekam
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByCodeProduct(String codeProduct);
}
