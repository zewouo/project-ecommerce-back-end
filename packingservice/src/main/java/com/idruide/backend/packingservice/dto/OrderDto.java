package com.idruide.backend.packingservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    private Integer id;
    private String costumerName;
    private Integer totalPrice;
    private Boolean shipped;
    private String createdAt;
    private String deliverDate;
    private String address;

}
