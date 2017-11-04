package com.gaoxi.enumeration.user;

import com.gaoxi.enumeration.BaseEnum;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 下午5:17
 * @description
 */
public enum UserTypeEnum implements BaseEnum {
    Person(1,"个人用户"),
    Company(2,"企业用户"),
    ADMIN(3,"管理员");

    private int code;
    private String msg;

    UserTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据code获取枚举
     * @param code
     * @return
     */
    public static UserTypeEnum getEnumByCode(int code) {
        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            if (userTypeEnum.getCode() == code) {
                return userTypeEnum;
            }
        }

        return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
