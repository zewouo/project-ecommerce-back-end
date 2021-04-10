package com.idruide.backend.orderservice.entities;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Thierry Kwekam
 */

@Getter
@Entity
@Data
@Table(name = "order_t")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer id;

    @Column (name = "orderNumber",nullable = false)
    private String orderNumber;

    @Column (name = "costumerName",nullable = false)
    private String costumerName;

    @Column (name = "totalPrice",nullable = false)
    private Integer totalPrice;

    @Column (name = "shipped")
    private Boolean shipped;

    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @Column (name = "deliver_date")
    private LocalDateTime deliverDate;


    @Column (name = "address")
    private String address;

    @OneToMany(targetEntity = OrderProduct.class,
            fetch = FetchType.EAGER, cascade = {CascadeType.ALL,CascadeType.REMOVE})
    private List<OrderProduct> orderProducts;


    public void addProducts(OrderProduct orderProduct) {
        if (orderProduct == null) return;
        if (this.orderProducts == null)
            this.orderProducts = new ArrayList<>();
        this.orderProducts.add(orderProduct);
    }


}

