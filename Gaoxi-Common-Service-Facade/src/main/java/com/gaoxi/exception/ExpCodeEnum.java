package com.gaoxi.exception;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:37
 * 全局的异常状态码 和 异常描述
 */
public enum ExpCodeEnum {

    UNKNOW_ERROR(10000, "未知异常"),
    USERNAME_NULL(10001, "用户名为空"),
    LOW_STOCKS(10002, "库存不足");

    private int code;
    private String message;

    private ExpCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
