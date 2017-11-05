package com.gaoxi.req.product;

import com.gaoxi.req.QueryReq;

/**
 * Created by lihang on 2017/11/5.
 */
public class CategoryQueryReq extends QueryReq {
    /** 类别id */
    private String id;

    /** 类别名称（模糊匹配） */
    private String categoryName;

    /** 父分类id (一级分类的parentId为空) */
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
