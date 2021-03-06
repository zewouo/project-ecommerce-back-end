package com.idruide.backend.packingservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Thierry Kwekam
 */
@Data
@Entity
@Table(name = "packing_t")
public class Packing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "packing_id")
    private Integer id;

    @Column(name = "codePacking", nullable = false)
    private String codePacking;


    @Column(name = "deliver_date", nullable = false)
    private LocalDateTime deliverDate;

    @Column(name = "comment", nullable = false)
    private String comment;


    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "order_id")
    private Order order;

}
