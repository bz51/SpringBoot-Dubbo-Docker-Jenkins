package com.gaoxi.enumeration;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 下午9:22
 * @description 排序规则枚举
 */
public enum OrderEnum {
    DESC(1,"DESC"),
    ASC(2,"ASC");

    private int code;
    private String msg;

    OrderEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(int code) {
        for (OrderEnum orderEnum : OrderEnum.values()) {
            if (orderEnum.code==code) {
                return orderEnum.msg;
            }
        }
        return null;
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
