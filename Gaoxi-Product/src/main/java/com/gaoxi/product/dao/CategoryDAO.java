package com.gaoxi.product.dao;

import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.req.product.CategoryQueryReq;
import com.gaoxi.rsp.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lihang on 2017/11/5.
 */
@Mapper
public interface CategoryDAO {
    /**
     * 新增类别
     * @param categoryEntity
     * @return
     */
    int createCategoty(CategoryEntity categoryEntity);

    /**
     * 修改类别,增量更新
     * @param categoryEntity
     * @return
     */
    int updateCategory(CategoryEntity categoryEntity);

    /**
     * 删除类别
     * @param categoryId
     * @return
     */
    int deleteCategory(String categoryId);

    /**
     * 查询所有类别（分页）
     * @param categoryQueryReq
     */
    List<CategoryEntity> findCategorys(CategoryQueryReq categoryQueryReq);
}
