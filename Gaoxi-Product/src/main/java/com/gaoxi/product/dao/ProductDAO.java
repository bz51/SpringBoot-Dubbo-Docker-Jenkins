package com.gaoxi.product.dao;

import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.req.product.ProdInsertReq;
import com.gaoxi.req.product.ProdQueryReq;
import com.gaoxi.req.product.ProdUpdateReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lihang on 2017/11/4.
 */
@Mapper
public interface ProductDAO {
    /**
     * 新增产品
     * @param prodInsertReq
     * @return
     */
    int createProduct(ProdInsertReq prodInsertReq);

    /**
     * 增量更新产品
     * @param prodUpdateReq
     */
    int updateProduct(ProdUpdateReq prodUpdateReq);

    /**
     * 多条件查询产品
     * @param prodQueryReq
     * @return
     */
    List<ProductEntity> findProducts(ProdQueryReq prodQueryReq);

    /**
     * 查询制定类别是否被使用
     * @param categoryId
     * @return
     */
    int findUsedCategory(String categoryId);
}
