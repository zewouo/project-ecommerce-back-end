package com.idruide.backend.packingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Thierry Kwekam
 */
@Data
@Builder
@AllArgsConstructor
public class PackingDto {

    private Integer id;
    private String code;
    private String deliverDate;
    private String comment;
    private String createdAt;
    private Integer orderId;
}
