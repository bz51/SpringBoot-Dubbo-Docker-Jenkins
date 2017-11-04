package com.gaoxi.facade.product;

import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.rsp.Result;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午8:43
 * @description
 */
public interface ProductService {
    /**
     * 新增产品
     * @param productEntity
     * @return
     */
    Result createProduct(ProductEntity productEntity);
}
