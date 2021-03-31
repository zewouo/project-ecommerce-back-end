package com.idruide.backend.catalogservice.dto;

import lombok.Data;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Data
public class ProductInput {

    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private Integer amount;
}
