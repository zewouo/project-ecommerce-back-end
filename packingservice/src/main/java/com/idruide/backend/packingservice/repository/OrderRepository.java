package com.idruide.backend.packingservice.repository;


import com.idruide.backend.packingservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thierry Kwekam
 */

public interface OrderRepository extends JpaRepository<Order, Integer> {
    //@Join(value = "orderProducts", type = Join.Type.FETCH)
    Order findByOrderNumber(String orderNumber);
}
