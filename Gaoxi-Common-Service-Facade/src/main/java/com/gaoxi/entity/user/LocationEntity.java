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

}
