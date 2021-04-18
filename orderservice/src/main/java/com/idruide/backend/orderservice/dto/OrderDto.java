package com.idruide.backend.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Thierry Kwekam
 */
@Getter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class OrderDto implements Serializable {

    private Integer id;
    private String orderNumber;
    private String costumerName;
    private Integer totalPrice;
    private Boolean shipped;
    private String createdAt;
    private String deliverDate;
    private String address;
    private List<OrderProductDto> orderProducts;

}
