package com.idruide.backend.orderservice.repository;

import com.idruide.backend.orderservice.entities.OrderCo;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 *
 * @author Thierry Kwekam
 */
public interface OrderRepository extends JpaRepository<OrderCo, Integer> {

}
