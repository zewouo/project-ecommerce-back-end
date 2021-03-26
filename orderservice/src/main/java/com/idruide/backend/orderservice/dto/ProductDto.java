package com.idruide.backend.orderservice.dto;

import com.idruide.backend.orderservice.entities.OrderCo;
import lombok.Data;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Data
public class ProductDto {

    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private Integer amount;

    private OrderCo orderCo;
}
