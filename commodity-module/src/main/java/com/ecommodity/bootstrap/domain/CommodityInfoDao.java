package com.ecommodity.bootstrap.domain;

import com.ecommodity.bootstrap.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 商品信息
 */
@Repository
public interface CommodityInfoDao extends JpaRepository<ProductEntity,Long> {
}
