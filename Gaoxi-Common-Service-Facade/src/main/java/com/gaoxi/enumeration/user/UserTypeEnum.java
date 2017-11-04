package com.gaoxi.enumeration.user;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 下午5:17
 * @description
 */
public enum UserTypeEnum {
    Person(1,"个人用户"),
    Company(2,"企业用户"),
    ADMIN(3,"管理员");

    private int code;
    private String msg;

    UserTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
