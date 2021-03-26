package com.idruide.backend.packingservice.packingservice.entities;


import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Data
@Entity
@Setter
@Table(name = "packing_t")
public class Packing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "packing_id")
    private Integer id;

    @Column(name = "code",nullable = false)
    private String code;

    @Column(name = "deliver_date",nullable = false)
    private LocalDate deliverDate;

    @Column(name = "comment",nullable = false)
    private String comment;

    @Column(name = "created_at",nullable = false)
    private LocalDate createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    private Order order;

}
