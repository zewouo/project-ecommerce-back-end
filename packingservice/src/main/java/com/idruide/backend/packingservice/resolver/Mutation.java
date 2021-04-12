package com.idruide.backend.packingservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.idruide.backend.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.service.PackingService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 *
 * @author Thierry Kwekam
 */
@Component
@Slf4j
public class Mutation implements GraphQLMutationResolver {

    Logger logger = LoggerFactory.getLogger(Mutation.class);


    private PackingService packingService;

    @Autowired
    public Mutation(PackingService packingService) {
        this.packingService = packingService;
    }

    public PackingDto createPacking(PackingDto packingDto) {
        log.info("Create Packing with code " + packingDto.getCode() + " in Packing service");
        return packingService.savePacking(packingDto);
    }

    public PackingDto updatePacking(PackingDto packingDto) {
        log.info("Update Packing with code " + packingDto.getCode() + " in Packing service");
        return packingService.savePacking(packingDto);
    }

    public PackingDto deletePacking(Integer packingId) {
        PackingDto packingDto = packingService.validateAndGetPackingById(packingId);
        log.info("Delete Packing with Id " + packingId + " in Packing service");
        packingService.deletePacking(packingDto);
        return packingDto;
    }

    public PackingDto addPackingOrder(Integer packingId, Integer orderId) {
        PackingDto packingDto = packingService.validateAndGetPackingById(packingId);
        log.info("Associate Packing " + packingId + " with Order " + orderId + " in Packing service");
        packingDto.setOrderId(orderId);
        return packingService.savePacking(packingDto);
    }

}
