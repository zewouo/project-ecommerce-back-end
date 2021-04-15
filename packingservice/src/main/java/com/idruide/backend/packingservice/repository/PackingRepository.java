package com.idruide.backend.packingservice.repository;


import com.idruide.backend.packingservice.entities.Packing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Thierry Kwekam
 */
public interface PackingRepository extends JpaRepository<Packing, Integer> {
      List<Packing> findByCodePacking(String codePacking);
}
