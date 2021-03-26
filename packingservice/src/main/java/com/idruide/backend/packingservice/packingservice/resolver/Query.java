package com.idruide.backend.packingservice.packingservice.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idruide.backend.packingservice.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.packingservice.service.PackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 *
 *
 * @author Thierry Kwekam
 */
@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    private PackingService packingService;

    public List<PackingDto> getAllPackings() { return packingService.getAllPackings(); }

    public PackingDto getPackingById(Integer packingId) {
        return packingService.validateAndGetPackingById(packingId);
    }


}
