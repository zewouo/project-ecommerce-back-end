package com.idruide.backend.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Thierry Kwekam
 */
@Getter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto implements Serializable {

    private List<Integer> productIds;


}
