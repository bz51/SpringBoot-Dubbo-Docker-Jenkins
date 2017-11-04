package com.gaoxi.product.dao;

import com.gaoxi.entity.product.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by lihang on 2017/11/4.
 */
@Mapper
public interface ProductDAO {
    /**
     * 新增产品
     * @param productEntity
     * @return
     */
    int createProduct(ProductEntity productEntity);
}
