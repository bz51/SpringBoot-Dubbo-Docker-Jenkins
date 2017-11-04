package com.gaoxi.entity.product;

import com.gaoxi.entity.user.UserEntity;

import java.io.Serializable;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午3:38
 * @description 产品品牌的实体类
 */
public class BrandEntity implements Serializable{

    /** 主键 */
    private String id;

    /** 产品品牌名称 */
    private String brand;

    /** 品牌logo url*/
    private String brandLogoUrl;

    /** 品牌所属企业 */
    private UserEntity companyEntity;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getBrandLogoUrl() {
        return brandLogoUrl;
    }

    public void setBrandLogoUrl(String brandLogoUrl) {
        this.brandLogoUrl = brandLogoUrl;
    }

    public UserEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(UserEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    @Override
    public String toString() {
        return "BrandEntity{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", brandLogo=" + brandLogoUrl +
                ", companyEntity=" + companyEntity +
                ", sort=" + sort +
                '}';
    }
}
