package com.idruide.backend.orderservice.repository;

import com.idruide.backend.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 *
 *
 * @author Thierry Kwekam
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByOrderNumber(String orderNumber);
    void delete(Order entity);

}
