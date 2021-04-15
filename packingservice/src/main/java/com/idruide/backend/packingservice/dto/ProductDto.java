package com.idruide.backend.packingservice.dto;

import lombok.Data;

/**
 * @author Thierry Kwekam
 */
@Data
public class ProductDto {
    private String codeProduct;
    private String name;
    private Integer price;
    private String description;
    private Integer quantity;
}
