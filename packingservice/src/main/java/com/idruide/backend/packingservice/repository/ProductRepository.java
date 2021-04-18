package com.idruide.backend.packingservice.repository;

import com.idruide.backend.packingservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thierry Kwekam
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {
    void delete(Product entity);
}
