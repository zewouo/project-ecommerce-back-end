package com.idruide.backend.packingservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.idruide.backend.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.service.PackingService;
import lombok.extern.slf4j.Slf4j;
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

    private PackingService packingService;

    @Autowired
    public Mutation(PackingService packingService) {
        this.packingService = packingService;
    }

    public PackingDto createPacking(PackingDto packingDto) {
        log.info("Create Packing with code :" + packingDto.getCodePacking() + " in Packing service");
        return this.packingService.savePacking(packingDto);
    }

    public PackingDto updatePacking(PackingDto packingDto) {
        log.info("Update Packing with code " + packingDto.getCodePacking() + " in Packing service");
        return this.packingService.savePacking(packingDto);
    }

    public PackingDto deletePacking(PackingDto packingDto) {
        PackingDto packing = packingService.getPackingByCode(packingDto.getCodePacking());
        log.info("Delete Packing with code: " + packingDto.getCodePacking() + " in Packing service");
        return this.packingService.deletePacking(packingDto);
    }

}
