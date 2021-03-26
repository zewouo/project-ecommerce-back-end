package com.idruide.backend.packingservice.packingservice.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Thierry Kwekam
 */
@Data
public class PackingDto {

    private Integer id;
    private String code;
    private LocalDate deliverDate;
    private String comment;
    private LocalDate createdAt;
    private OrderDto order;
}
