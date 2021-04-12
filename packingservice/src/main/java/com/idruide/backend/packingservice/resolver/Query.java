package com.idruide.backend.packingservice.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idruide.backend.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.dto.ProductDto;
import com.idruide.backend.packingservice.service.PackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Thierry Kwekam
 */
@Component
@Slf4j
public class Query implements GraphQLQueryResolver {

    private final PackingService packingService;

    @Autowired
    public Query(PackingService packingService) {
        this.packingService = packingService;
    }

    public List<PackingDto> getAllPackings() {
        log.info("Get all Packing  in Packing service");
        return packingService.getAllPackings();
    }

    public PackingDto getPackingByCode(String codePacking) {
        log.info("Get Packing by code: " + codePacking);
        return packingService.getPackingByCode(codePacking);
    }

   public List<ProductDto> getPackingSlipDetails(String codePacking){
        log.info("Get list of products  by Packing  code: " + codePacking);
        return packingService.getProductsByPacking(codePacking);
    }


}
