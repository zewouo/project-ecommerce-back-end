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
    private static final long serialVersionUID = -463935182199049241L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer id;

    @Column (name = "order_number",nullable = false)
    private String orderNumber;

    @Column (name = "costumer_name",nullable = false)
    private String costumerName;

    @Column (name = "total_price",nullable = false)
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
            fetch = FetchType.EAGER, cascade = {CascadeType.ALL,CascadeType.REMOVE}, mappedBy = "orderId")
    private List<OrderProduct> orderProducts;


    public void addProducts(OrderProduct orderProduct) {
        if (orderProduct == null) return;
        if (this.orderProducts == null)
            this.orderProducts = new ArrayList<>();
        this.orderProducts.add(orderProduct);
    }


}

