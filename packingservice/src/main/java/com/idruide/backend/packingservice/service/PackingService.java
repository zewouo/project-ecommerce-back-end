package com.idruide.backend.packingservice.service;


import com.idruide.backend.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.dto.ProductDto;

import java.util.List;

/**
 *
 *
 * @author Thierry Kwekam
 */
public interface PackingService {

    List<PackingDto> getAllPackings();

    PackingDto getPackingByCode(String codePacking);

    List<ProductDto> getProductsByPacking(String codePacking);

    PackingDto savePacking(PackingDto packingDto);

    PackingDto deletePacking(PackingDto packingDto);


}
