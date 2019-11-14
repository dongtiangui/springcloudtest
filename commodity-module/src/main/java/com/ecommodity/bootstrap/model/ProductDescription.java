package com.ecommodity.bootstrap.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品描述表
 */

@Entity
@Table(name = "product_description")
public class ProductDescription implements Serializable {
    private static final long serialVersionUID = 5256581049137533215L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long descriptionId;

}
