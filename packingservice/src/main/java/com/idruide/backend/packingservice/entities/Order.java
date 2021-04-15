package com.idruide.backend.packingservice.entities;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author Thierry Kwekam
 */

@Getter
@Entity
@Data
@Table(name = "order_t")
public class Order implements Serializable {
    private static final long serialVersionUID = -463935182199049241L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "costumer_name", nullable = false)
    private String costumerName;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "shipped")
    private Boolean shipped;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deliver_date")
    private LocalDateTime deliverDate;

    @Column(name = "address")
    private String address;

    @Fetch(FetchMode.JOIN)
    @OneToMany(targetEntity = OrderProduct.class,
            fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.REMOVE}, mappedBy = "orderId")
    private List<OrderProduct> orderProducts;

}
