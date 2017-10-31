package com.gaoxi.entity.product;

import java.io.Serializable;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午2:54
 * @description 产品分类的实体类
 */
public class CategoryEntity implements Serializable{

    /** 主键 */
    private String id;

    /** 分类名称 */
    private String category;

    /** 父分类id (一级分类的parentId为空) */
    private String parentId;

    /** 排序（权值越高，排名越前）*/
    private int sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", parentId='" + parentId + '\'' +
                ", sort=" + sort +
                '}';
    }
}
