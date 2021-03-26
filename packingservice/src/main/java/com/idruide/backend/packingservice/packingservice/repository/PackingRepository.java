package com.idruide.backend.packingservice.packingservice.repository;


import com.idruide.backend.packingservice.packingservice.entities.Packing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 *
 * @author Thierry Kwekam
 */
public interface PackingRepository extends JpaRepository<Packing, Integer> {

}
