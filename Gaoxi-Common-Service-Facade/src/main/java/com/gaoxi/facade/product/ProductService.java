package com.gaoxi.facade.product;

import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.req.product.*;
import com.gaoxi.rsp.Result;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午8:43
 * @description
 */
public interface ProductService {
    /**
     * 新增产品
     * @param prodInsertReq
     * @return
     */
    Result createProduct(ProdInsertReq prodInsertReq);

    /**
     * 增量更新产品
     * @param prodUpdateReq
     * @return
     */
    Result updateProduct(ProdUpdateReq prodUpdateReq);

    /**
     * 多条件查询所有产品（支持分页）
     * @param prodQueryReq
     * @return
     */
    Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq);

    /**
     * 新增类别
     * @param categoryEntity
     * @return
     */
    Result createCategoty(CategoryEntity categoryEntity);

    /**
     * 增量更新类别
     * @param categoryEntity
     * @return
     */
    Result modifyCategory(CategoryEntity categoryEntity);

    /**
     * 删除类别
     * @param categoryId
     * @return
     */
    Result deleteCategory(String categoryId);

    /**
     * 新增品牌
     * @param brandInsertReq
     * @return
     */
    Result createBrand(BrandInsertReq brandInsertReq);

    /**
     * 增量更新品牌
     * @param brandInsertReq
     * @return
     */
    Result modifyBrand(BrandInsertReq brandInsertReq);

    /**
     * 多条件查询所有类别（支持分页）
     * @param categoryQueryReq
     * @return
     */
    Result<List<CategoryEntity>> findCategorys(CategoryQueryReq categoryQueryReq);

    /**
     * 多条件查询所有品牌（支持分页）
     * @param brandQueryReq
     * @return
     */
    Result<List<BrandEntity>> findBrands(BrandQueryReq brandQueryReq);
}
