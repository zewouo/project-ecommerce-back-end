package com.idruide.backend.packingservice.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.idruide.backend.packingservice.dto.PackingDto;
import com.idruide.backend.packingservice.service.PackingService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 *
 *
 * @author Thierry Kwekam
 */
@Component
@Slf4j
public class Query implements GraphQLQueryResolver {

    Logger logger = LoggerFactory.getLogger(Query.class);

    private PackingService packingService;

    @Autowired
    public Query(PackingService packingService) {

        this.packingService = packingService;
    }

    public List<PackingDto> getAllPackings() {
        log.info("get all Packing  in Packing service");
        return packingService.getAllPackings();
    }

    public PackingDto getPackingById(Integer packingId) {
        log.info("get Packing for Id " + packingId);
        return packingService.validateAndGetPackingById(packingId);
    }


}
