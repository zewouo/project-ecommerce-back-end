package com.idruide.backend.orderservice.entities;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer id;
    private String costumerName;
    private Integer totalPrice;
    private Boolean shipped;
    private LocalDateTime createdAt;
    private LocalDateTime deliverDate;
    private String address;

    @OneToMany(targetEntity = Product.class,
            fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Product> products;


    public void addProducts(Product product) {
        if (product == null) return;
        if (this.products == null)
            this.products = new ArrayList<>();
        this.products.add(product);
    }


}
