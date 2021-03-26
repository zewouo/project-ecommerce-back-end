package com.idruide.backend.packingservice.packingservice.entities;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 *
 *
 * @author Thierry Kwekam
 */
@Entity
@Data
@Table(name = "order_t")
public class Order {

    @Id
    @Column(name = "order_id")
    private Integer id;
    private Integer totalPrice;
    private LocalDate createdAt;
    private LocalDate deliverDate;


}
