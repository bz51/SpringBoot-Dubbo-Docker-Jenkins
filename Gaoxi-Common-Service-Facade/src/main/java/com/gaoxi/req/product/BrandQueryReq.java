package com.gaoxi.req.product;

import com.gaoxi.req.QueryReq;

/**
 * Created by lihang on 2017/11/5.
 */
public class BrandQueryReq extends QueryReq {
    /** 主键 */
    private String id;

    /** 产品品牌名称 */
    private String brand;

    /** 品牌所属企业 */
    private String companyEntityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompanyEntityId() {
        return companyEntityId;
    }

    public void setCompanyEntityId(String companyEntityId) {
        this.companyEntityId = companyEntityId;
    }
}
