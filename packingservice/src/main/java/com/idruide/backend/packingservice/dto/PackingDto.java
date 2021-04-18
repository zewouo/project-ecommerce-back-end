package com.idruide.backend.packingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Thierry Kwekam
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackingDto implements Serializable {

    private Integer id;
    private String codePacking;
    private String deliverDate;
    private String comment;
    private String createdAt;
    private String orderNumber;
}
