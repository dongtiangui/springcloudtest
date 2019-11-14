package com.ecommodity.bootstrap.model;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品属性
 */
@Entity
@Table(name = "product_brand")
public class ProductBrand implements Serializable {

    private static final long serialVersionUID = -2429814678225619458L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;
}
