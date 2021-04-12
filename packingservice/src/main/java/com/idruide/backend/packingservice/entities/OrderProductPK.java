package com.idruide.backend.packingservice.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 *
 *
 * @author Thierry Kwekam
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
@Builder
public class OrderProductPK implements Serializable {

    private static final long serialVersionUID = -463235182199049241L;

    @Column(name = "order_number")
    private String  orderNumber;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}



