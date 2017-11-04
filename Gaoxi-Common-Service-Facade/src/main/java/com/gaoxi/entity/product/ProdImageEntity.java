package com.gaoxi.entity.product;

import java.io.Serializable;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午3:45
 * @description 产品图片的实体类
 */
public class ProdImageEntity implements Serializable{

    /** 主键 */
    private String id;

    /** 图片的URL */
    private String imageURL;

    /** 图片所属产品的ID */
    private String productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProdImageEntity{" +
                "id='" + id + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
