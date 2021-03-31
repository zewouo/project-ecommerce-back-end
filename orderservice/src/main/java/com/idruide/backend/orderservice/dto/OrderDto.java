package com.idruide.backend.orderservice.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author Thierry Kwekam
 */
@Getter
@Data
public class OrderDto {

    private Integer id;
    private String costumerName;
    private Integer totalPrice;
    private Boolean shipped;
    private String createdAt;
    private String deliverDate;
    private String address;
    private List<Integer> productIds;


}
