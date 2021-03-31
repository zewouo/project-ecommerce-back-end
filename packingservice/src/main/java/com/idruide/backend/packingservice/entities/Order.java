package com.idruide.backend.packingservice.entities;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 *
 *
 * @author Thierry Kwekam
 */

@Getter
@Entity
@Data
@Table(name = "order_t")
public class Order {
    @Id
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "costumer_name")
    private String costumerName;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "shipped", nullable = false)
    private Boolean shipped;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deliver_date")
    private LocalDateTime deliverDate;

    @Column(name = "address")
    private String address;

}
