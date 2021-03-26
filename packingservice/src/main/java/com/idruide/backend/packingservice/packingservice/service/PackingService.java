package com.idruide.backend.packingservice.packingservice.service;


import com.idruide.backend.packingservice.packingservice.dto.PackingDto;
import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */
public interface PackingService {

    List<PackingDto> getAllPackings();

    PackingDto validateAndGetPackingById(Integer id);

    PackingDto savePacking(PackingDto packingDto);

    void deletePacking(PackingDto packingDto);

}
