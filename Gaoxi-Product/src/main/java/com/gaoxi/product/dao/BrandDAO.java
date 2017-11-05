package com.gaoxi.product.dao;

import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.req.product.BrandInsertReq;
import com.gaoxi.req.product.BrandQueryReq;
import com.gaoxi.req.product.CategoryQueryReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lihang on 2017/11/5.
 */
@Mapper
public interface BrandDAO {
    /**
     * 新增品牌
     * @param brandInsertReq
     * @return
     */
    int createBrand(BrandInsertReq brandInsertReq);

    /**
     * 增量更新品牌
     * @param brandInsertReq
     */
    int updateBrand(BrandInsertReq brandInsertReq);


    /**
     * 查询所有品牌（支持分页）
     * @param brandQueryReq
     * @return
     */
    List<BrandEntity> findBrands(BrandQueryReq brandQueryReq);
}
