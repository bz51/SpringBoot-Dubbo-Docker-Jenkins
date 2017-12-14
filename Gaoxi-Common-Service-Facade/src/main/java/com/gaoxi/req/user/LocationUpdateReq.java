package com.gaoxi.req.user;

import com.gaoxi.req.AbsReq;

/**
 * @author 大闲人柴毛毛
 * @date 2017/12/14 上午9:31
 *
 * @description 收货地址更新请求
 */
public class LocationUpdateReq extends AbsReq {
    /** 收货地址ID */
    private String locationId;

    /** 详细地址 */
    private String location;

    /** 收货人姓名 */
    private String name;

    /** 收货人手机号 */
    private String phone;

    /** 邮编 */
    private String postCode;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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
        return "LocationUpdateReq{" +
                "locationId='" + locationId + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
