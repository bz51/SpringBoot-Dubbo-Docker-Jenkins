package com.gaoxi.entity.order;

import com.gaoxi.entity.product.ProductEntity;

import java.io.Serializable;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 上午10:52
 *
 * @description 产品-订单关系(用于指定某一订单中某一产品的数量)
 */
public class ProductOrderEntity implements Serializable {

    /** 订单ID */
    private String orderId;

    /** 产品详情 */
    private ProductEntity productEntity;

    /** 产品数量 */
    private int count;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "ProductOrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", productEntity=" + productEntity +
                ", count=" + count +
                '}';
    }
}
