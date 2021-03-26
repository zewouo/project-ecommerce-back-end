package com.idruide.backend.orderservice.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Thierry Kwekam
 */
@Getter
@Data
public class OrderDto {

    private Integer id;
    private Integer totalPrice;
    private String createdAt;
    private String deliverDate;
    private List<ProductDto> productDtos;


}
