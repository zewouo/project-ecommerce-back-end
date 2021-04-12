package com.idruide.backend.packingservice.repository;

import com.idruide.backend.packingservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author Thierry Kwekam
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCodeProduct(String codeProduct);
    List<Product> findByName(String name);

    void delete(Product entity);
}
