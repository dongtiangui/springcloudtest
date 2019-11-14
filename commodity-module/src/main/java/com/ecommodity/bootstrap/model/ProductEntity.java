package com.ecommodity.bootstrap.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品表
 */
@Entity
@Table(name = "t_product")
public class ProductEntity {
    /**
     * 商品ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    /**
     * 商品名
     */
    @Column
    private String productName;
    /**
     *商品型号
     */
    @Column
    private String marque;

    /**
     * 商品二维码
     */
    @Column
    private String barcode;

    /**
     * 类型
     */
    private String type_id;
    /**
     * 类别
     */
    private String category_id;

    /**
     * 品牌
     */
    private String brand_id;

    /**
     * 价格
     */
    private double price;

    /**
     * 市场价
     */
    private double market_price;

    /**
     * 成本
     */
    private double cost_price;

    /**
     * 库存
     */
    private double stock;

    /**
     * 库存警告
     * columnDefinition = "enum('true','false')",
     */
    @Column(length = 32)
    private String stock_warn;

    /**
     * 积分
     */

    private String integral;
    /**
     * 商品图片地址
     */
    @Column
    private String picture_url;

    /**
     * 状态
     *
     * columnDefinition = "enum('下架','上架','预售')"
     */
    @Column
    private String status;

    /**
     * 创建时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date createTime;
}
