package com.idruide.backend.packingservice.repository;

import com.idruide.backend.packingservice.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 *
 * @author Thierry Kwekam
 */
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

}
