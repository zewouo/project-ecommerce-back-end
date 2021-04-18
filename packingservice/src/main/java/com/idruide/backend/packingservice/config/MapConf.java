package com.idruide.backend.packingservice.config;


import com.idruide.backend.packingservice.mapper.PackingMapper;
import com.idruide.backend.packingservice.mapper.ProductMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Thierry Kwekam
 */

@Configuration
public class MapConf {

    @Bean
    PackingMapper getPackingMapper() {
        return Mappers.getMapper(PackingMapper.class);
    }

    @Bean
    ProductMapper getProductMapper() {
        return Mappers.getMapper(ProductMapper.class);
    }


}
