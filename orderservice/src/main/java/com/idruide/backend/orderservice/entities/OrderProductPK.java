package com.idruide.backend.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode(of={"orderNumber","codeProduct"})
public class OrderProductPK implements Serializable {

    private static final long serialVersionUID = -463235182199049241L;

    @Column(name = "orderNumber")
    private String orderNumber;

    @Column(name = "codeProduct")
    private String codeProduct;

}


