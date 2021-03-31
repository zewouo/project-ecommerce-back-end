package com.idruide.backend.packingservice.dto;

import lombok.Data;

/**
 * @author Thierry Kwekam
 */
@Data
public class PackingDto {

    private Integer id;
    private String code;
    private String deliverDate;
    private String comment;
    private String createdAt;
    private Integer orderId;
}