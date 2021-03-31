package com.idruide.backend.catalogservice.dto;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Thierry Kwekam
 */
@Getter
@Data
public class OrderDto implements Serializable {

    private Integer id;
    private String costumerName;
    private Integer totalPrice;
    private Boolean shipped;
    private String createdAt;
    private String deliverDate;
    private String address;
    private List<Integer> productIds;


}
