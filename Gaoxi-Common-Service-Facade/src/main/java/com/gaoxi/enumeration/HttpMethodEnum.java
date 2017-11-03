package com.gaoxi.enumeration;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 上午11:09
 *
 * @description HTTP请求方式的枚举类
 */
public enum HttpMethodEnum {
    GET(1,"GET"),
    POST(2,"POST"),
    PUT(3,"PUT"),
    DELETE(4,"DELETE");

    private int code;
    private String msg;

    HttpMethodEnum(int code, String msg) {
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
