package com.gaoxi.entity.product;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.product.ProdStateEnum;

import java.io.Serializable;
import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @Date 2017/10/31 下午2:20
 * 产品实体类
 */
public class ProductEntity implements Serializable {

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
    private CategoryEntity topCateEntity;

    /** 产品所属二级分类 */
    private CategoryEntity subCategEntity;

    /** 产品所属品牌 */
    private BrandEntity brandEntity;

    /** 是否上架 {@link com.gaoxi.enumeration.product.ProdStateEnum} */
    private ProdStateEnum prodStateEnum;

    /** 产品图片 */
    private List<ProdImageEntity> prodImageEntityList;

    /** 产品详情 */
    private String content;

    /** 产品所属企业信息 */
    private UserEntity companyEntity;


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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public CategoryEntity getTopCateEntity() {
        return topCateEntity;
    }

    public void setTopCateEntity(CategoryEntity topCateEntity) {
        this.topCateEntity = topCateEntity;
    }

    public CategoryEntity getSubCategEntity() {
        return subCategEntity;
    }

    public void setSubCategEntity(CategoryEntity subCategEntity) {
        this.subCategEntity = subCategEntity;
    }

    public BrandEntity getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(BrandEntity brandEntity) {
        this.brandEntity = brandEntity;
    }

    public ProdStateEnum getProdStateEnum() {
        return prodStateEnum;
    }

    public void setProdStateEnum(ProdStateEnum prodStateEnum) {
        this.prodStateEnum = prodStateEnum;
    }

    public List<ProdImageEntity> getProdImageEntityList() {
        return prodImageEntityList;
    }

    public void setProdImageEntityList(List<ProdImageEntity> prodImageEntityList) {
        this.prodImageEntityList = prodImageEntityList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(UserEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + id + '\'' +
                ", prodName='" + prodName + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", shopPrice='" + shopPrice + '\'' +
                ", stock=" + stock +
                ", sales=" + sales +
                ", weight='" + weight + '\'' +
                ", topCateEntity=" + topCateEntity +
                ", subCategEntity=" + subCategEntity +
                ", brandEntity=" + brandEntity +
                ", prodStateEnum=" + prodStateEnum +
                ", prodImageEntityList=" + prodImageEntityList +
                ", content='" + content + '\'' +
                ", companyEntity=" + companyEntity +
                '}';
    }
}
