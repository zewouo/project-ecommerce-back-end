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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "amount",nullable = false)
    private Integer amount;




}
