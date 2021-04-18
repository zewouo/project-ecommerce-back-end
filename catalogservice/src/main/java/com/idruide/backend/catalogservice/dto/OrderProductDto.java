package com.idruide.backend.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Getter
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderProductDto {
    private String codeProduct;
    private String orderNumber;
    private Integer quantity;
}
