package com.idruide.backend.packingservice.packingservice.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import com.idruide.backend.packingservice.packingservice.dto.OrderDto;
import com.idruide.backend.packingservice.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.packingservice.mapper.OrderMapper;
import com.idruide.backend.packingservice.packingservice.mapper.PackingMapper;
import com.idruide.backend.packingservice.packingservice.service.PackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 *
 * @author Thierry Kwekam
 */
@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private PackingService packingService;


    public PackingDto createPacking(PackingDto packingDto){return packingService.savePacking(packingDto);}

    public PackingDto updatePacking(PackingDto packingDto) {return packingService.savePacking(packingDto);}

    public PackingDto deletePacking(Integer packingId) {
        PackingDto packingDto = packingService.validateAndGetPackingById(packingId);
        packingService.deletePacking(packingDto);
        return packingDto;
    }

    public PackingDto addPackingOrder(Integer packingId, OrderDto orderDto) {
        PackingDto packingDto = packingService.validateAndGetPackingById(packingId);
        packingDto.setOrder(orderDto);
        return packingService.savePacking(packingDto);
    }


}
