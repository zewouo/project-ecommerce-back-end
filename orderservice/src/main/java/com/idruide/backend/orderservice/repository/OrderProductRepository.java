package com.idruide.backend.orderservice.repository;

import com.idruide.backend.orderservice.entities.OrderProduct;
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
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    default List<OrderProduct>  retrieveProductsByIds(List<Integer> orderProductIds){
        return Optional.ofNullable(orderProductIds)
                .map(List::stream).orElseGet(Stream::empty)
                .map(currentId -> this.findById(currentId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    };

    //List<Product> findByCodeProduct(String codeProduct);
   // List<OrderProduct> findByCodeProductIn(Collection<String> codeProduct);





}
