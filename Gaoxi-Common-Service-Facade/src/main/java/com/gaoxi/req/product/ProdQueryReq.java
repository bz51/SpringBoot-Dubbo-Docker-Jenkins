package com.gaoxi.req.product;

import com.gaoxi.req.QueryReq;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午7:37
 * @description 产品查询请求
 */
public class ProdQueryReq extends QueryReq {

    /** 产品id */
    private String id;

    /** 产品名称（模糊匹配） */
    private String prodName;

    /** 本店价格下限 */
    private String shopPriceStart;
    /** 本店价格上限 */
    private String shopPriceEnd;

    /** 一级类别id */
    private String topCategoryId;
    /** 二级类别id */
    private String subCategoryId;

    /** 品牌id */
    private String brandId;

    /** 产品状态码 {@link com.gaoxi.enumeration.product.ProdStateEnum} */
    private Integer prodStateCode;

    /** 公司id */
    private String companyId;


    /** 根据价格排序 {@link com.gaoxi.enumeration.OrderEnum} */
    private Integer orderByPrice;

    /** 根据销量排序 {@link com.gaoxi.enumeration.OrderEnum} */
    private Integer orderBySales;


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

    public String getShopPriceStart() {
        return shopPriceStart;
    }

    public void setShopPriceStart(String shopPriceStart) {
        this.shopPriceStart = shopPriceStart;
    }

    public String getShopPriceEnd() {
        return shopPriceEnd;
    }

    public void setShopPriceEnd(String shopPriceEnd) {
        this.shopPriceEnd = shopPriceEnd;
    }

    public String getTopCategoryId() {
        return topCategoryId;
    }

    public void setTopCategoryId(String topCategoryId) {
        this.topCategoryId = topCategoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Integer getProdStateCode() {
        return prodStateCode;
    }

    public void setProdStateCode(Integer prodStateCode) {
        this.prodStateCode = prodStateCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getOrderByPrice() {
        return orderByPrice;
    }

    public void setOrderByPrice(Integer orderByPrice) {
        this.orderByPrice = orderByPrice;
    }

    public Integer getOrderBySales() {
        return orderBySales;
    }

    public void setOrderBySales(Integer orderBySales) {
        this.orderBySales = orderBySales;
    }

    @Override
    public String toString() {
        return "ProdQueryReq{" +
                "id='" + id + '\'' +
                ", prodName='" + prodName + '\'' +
                ", shopPriceStart='" + shopPriceStart + '\'' +
                ", shopPriceEnd='" + shopPriceEnd + '\'' +
                ", topCategoryId='" + topCategoryId + '\'' +
                ", subCategoryId='" + subCategoryId + '\'' +
                ", brandId='" + brandId + '\'' +
                ", prodStateCode=" + prodStateCode +
                ", companyId='" + companyId + '\'' +
                ", orderByPrice=" + orderByPrice +
                ", orderBySales=" + orderBySales +
                ", page=" + page +
                ", numPerPage=" + numPerPage +
                '}';
    }
}
