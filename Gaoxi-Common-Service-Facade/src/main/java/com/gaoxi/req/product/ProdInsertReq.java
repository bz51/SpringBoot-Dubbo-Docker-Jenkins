package com.gaoxi.req.product;

import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.entity.product.ProdImageEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.product.ProdStateEnum;
import com.gaoxi.req.AbsReq;

import java.util.List;

/**
 * Created by lihang on 2017/11/4.
 */
public class ProdInsertReq extends AbsReq {
    /** 产品ID（主键） */
    private String id;

    /** 产品名称 */
    private String prodName;

    /** 市场价（保留两位小数，使用字符串类型存储，计算时将其转为数值型） */
    private String marketPrice;

    /** 本店价 */
    private String shopPrice;

    /** 库存 */
    private int stock;

    /** 销量 */
    private int sales;

    /** 产品重量 */
    private String weight;

    /** 产品所属一级分类 */
    private String topCateEntityID;

    /** 产品所属二级分类 */
    private String subCategEntityID;

    /** 产品所属品牌 */
    private String brandEntityID;

    /** 是否上架 {@link com.gaoxi.enumeration.product.ProdStateEnum} */
    private Integer prodState;

    /** 产品详情 */
    private String content;

    /** 产品所属企业信息 */
    private String companyEntityID;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTopCateEntityID() {
        return topCateEntityID;
    }

    public void setTopCateEntityID(String topCateEntityID) {
        this.topCateEntityID = topCateEntityID;
    }

    public String getSubCategEntityID() {
        return subCategEntityID;
    }

    public void setSubCategEntityID(String subCategEntityID) {
        this.subCategEntityID = subCategEntityID;
    }

    public String getBrandEntityID() {
        return brandEntityID;
    }

    public void setBrandEntityID(String brandEntityID) {
        this.brandEntityID = brandEntityID;
    }

    public Integer getProdState() {
        return prodState;
    }

    public void setProdState(Integer prodState) {
        this.prodState = prodState;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompanyEntityID() {
        return companyEntityID;
    }

    public void setCompanyEntityID(String companyEntityID) {
        this.companyEntityID = companyEntityID;
    }

    @Override
    public String toString() {
        return "ProdInsertReq{" +
                "id='" + id + '\'' +
                ", prodName='" + prodName + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", shopPrice='" + shopPrice + '\'' +
                ", stock=" + stock +
                ", sales=" + sales +
                ", weight='" + weight + '\'' +
                ", topCateEntityID='" + topCateEntityID + '\'' +
                ", subCategEntityID='" + subCategEntityID + '\'' +
                ", brandEntityID='" + brandEntityID + '\'' +
                ", prodState=" + prodState +
                ", content='" + content + '\'' +
                ", companyEntityID='" + companyEntityID + '\'' +
                '}';
    }
}
