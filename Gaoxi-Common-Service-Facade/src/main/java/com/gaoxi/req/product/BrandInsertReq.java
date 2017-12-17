package com.gaoxi.req.product;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.req.AbsReq;

/**
 * Created by lihang on 2017/11/5.
 */
public class BrandInsertReq extends AbsReq {
    /** 主键 */
    private String id;

    /** 产品品牌名称 */
    private String brand;

    /** 品牌logo url*/
    private String brandLogoUrl;

    /** 品牌所属企业 */
    private String companyEntityId;

    /** 品牌排序（权值越高，排名越前） */
    private int sort;

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

    public String getBrandLogoUrl() {
        return brandLogoUrl;
    }

    public void setBrandLogoUrl(String brandLogoUrl) {
        this.brandLogoUrl = brandLogoUrl;
    }

    public String getCompanyEntityId() {
        return companyEntityId;
    }

    public void setCompanyEntityId(String companyEntityId) {
        this.companyEntityId = companyEntityId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "BrandInsertReq{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", brandLogoUrl='" + brandLogoUrl + '\'' +
                ", companyEntityId='" + companyEntityId + '\'' +
                ", sort=" + sort +
                '}';
    }
}
