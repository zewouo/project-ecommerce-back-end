package com.idruide.backend.packingservice.dto;

import lombok.Data;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Data
public class OrderDto {

    private Integer id;
    private String costumerName;
    private Integer totalPrice;
    private Boolean shipped;
    private String createdAt;
    private String deliverDate;
    private String address;

}
