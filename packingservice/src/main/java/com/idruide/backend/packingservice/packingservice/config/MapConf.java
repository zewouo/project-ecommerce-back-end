package com.idruide.backend.packingservice.packingservice.config;


import com.idruide.backend.packingservice.packingservice.mapper.OrderMapper;
import com.idruide.backend.packingservice.packingservice.mapper.PackingMapper;
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
    PackingMapper getProductMapper(){ return Mappers.getMapper(PackingMapper.class); }

    @Bean
    OrderMapper getOrderMapper(){
        return Mappers.getMapper(OrderMapper.class);
    }

}
