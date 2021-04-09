package com.idruide.backend.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderProductDto {
    private String codeProduct;
    private String orderNumber;
    private Integer quantity;
}
