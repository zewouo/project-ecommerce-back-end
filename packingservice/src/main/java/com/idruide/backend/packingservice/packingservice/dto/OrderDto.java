package com.idruide.backend.packingservice.packingservice.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Data
public class OrderDto {

    private Integer id;
    private Integer totalPrice ;
    private LocalDate CreatedAt ;
    private LocalDate DeliverDate ;


}
