package com.idruide.backend.orderservice.config;


import com.idruide.backend.orderservice.mapper.OrderMapper;
import com.idruide.backend.orderservice.mapper.ProductMapper;
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
    ProductMapper getProductMapper(){ return Mappers.getMapper(ProductMapper.class); }

    @Bean
    OrderMapper getOrderMapper(){
        return Mappers.getMapper(OrderMapper.class);
    }

}
