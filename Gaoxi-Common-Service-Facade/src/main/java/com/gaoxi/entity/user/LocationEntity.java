package com.gaoxi.entity.user;

import java.io.Serializable;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 上午11:40
 *
 * @description 地址信息
 */
public class LocationEntity implements Serializable{

    /** 主键 */
    private String id;

    /** 详细地址 */
    private String location;

    /** 收货人姓名 */
    private String name;

    /** 收货人手机号 */
    private String phone;

    /** 邮编 */
    private String postCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
