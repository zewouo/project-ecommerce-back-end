package com.idruide.backend.orderservice.entities;

import lombok.Data;
import lombok.Getter;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


/**
 * @author Thierry Kwekam
 */

@Getter
@Entity
@Data
@Table(name = "order_t")
public class OrderCo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer totalPrice;
    private LocalDate createdAt;
    private LocalDate deliverDate;

    @OneToMany(targetEntity= Product.class,
            fetch=FetchType.EAGER, cascade = {CascadeType.ALL,CascadeType.REMOVE})
    private List<Product> products;


/*    public void addProducts(Product product) {
        if (product == null) return;
        if (this.products == null)
            this.products = new ArrayList<>();
        this.products.add(product);
    }*/


}
