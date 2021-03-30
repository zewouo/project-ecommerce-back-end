package com.idruide.backend.orderservice.repository;

import com.idruide.backend.orderservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *
 *
 * @author Thierry Kwekam
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    default List<Product>  retrieveProductsByIds(List<Integer> productIds){
        return Optional.ofNullable(productIds)
                .map(List::stream).orElseGet(Stream::empty)
                .map(currentId -> this.findById(currentId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    };
}
