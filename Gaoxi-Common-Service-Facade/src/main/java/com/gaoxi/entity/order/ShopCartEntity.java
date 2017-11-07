package com.gaoxi.entity.order;

import com.gaoxi.entity.product.ProductEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 上午10:10
 *
 * @description 购物车实体类
 */
public class ShopCartEntity implements Serializable {

    /** 主键 */
    private String id;

    /** 用户id */
    private String userId;

    /** 产品详情（在数据库中以product_id表示） */
    private ProductEntity productEntity;

    /** 数量 */
    private int count;

    /** 添加时间 */
    private Timestamp time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ShopCartEntity{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", productEntity=" + productEntity +
                ", count=" + count +
                ", time=" + time +
                '}';
    }
}
