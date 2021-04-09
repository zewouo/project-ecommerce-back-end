package com.idruide.backend.orderservice.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(of = {"id"})
@Table(name = "order_product_t")
public class OrderProduct {

    @EmbeddedId
    private OrderProductPK id;

    @ManyToOne(targetEntity = Order.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "orderNumber", insertable = false, updatable = false,
            nullable = false,referencedColumnName = "orderNumber")
    private Order order;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeProduct",insertable = false, updatable = false,referencedColumnName = "codeProduct")
    private Product product;

}
