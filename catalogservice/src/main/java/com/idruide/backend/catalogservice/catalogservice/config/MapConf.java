package com.idruide.backend.catalogservice.catalogservice.config;

import com.idruide.backend.catalogservice.catalogservice.mapper.ProductMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Configuration
public class MapConf {

    @Bean
    ProductMapper getMapper(){
        return Mappers.getMapper(ProductMapper.class);
    }

}
