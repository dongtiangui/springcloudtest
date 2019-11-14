package com.ecommodity.bootstrap.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品统计表
 */
@Entity
@Table(name = "t_product_statistics")
public class ProductStatistics implements Serializable {

    private static final long serialVersionUID = -8766308479526789628L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statisticsId;
}
