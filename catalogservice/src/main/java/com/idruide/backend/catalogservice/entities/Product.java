package com.idruide.backend.catalogservice.entities;


import lombok.Data;
import javax.persistence.*;

/**
 *
 *
 * @author Thierry Kwekam
 */

@Entity
@Data
@Table(name = "product_t")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer id;

    @Column (name = "codeProduct",nullable = false)
    private String codeProduct;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "quantity" ,nullable = false)
    private Integer quantity;


}
