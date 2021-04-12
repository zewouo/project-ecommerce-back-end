package com.idruide.backend.catalogservice.repository;


import com.idruide.backend.catalogservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 *
 *
 * @author Thierry Kwekam
 */

public interface  ProductRepository extends JpaRepository<Product, Integer> {
    Collection<Product> findByNameIgnoreCase(String nameNormSpace);
}
