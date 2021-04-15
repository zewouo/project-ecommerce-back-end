package com.idruide.backend.orderservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author Thierry Kwekam
 */

@Data
@Entity
@Table(name = "order_product_t")
public class OrderProduct implements Serializable {

    @EmbeddedId
    private OrderProductPK id;

    @Column(name = "order_number", nullable = false, insertable = false, updatable = false)
    private String orderNumber;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orderId;

    private transient String productCode;

}

