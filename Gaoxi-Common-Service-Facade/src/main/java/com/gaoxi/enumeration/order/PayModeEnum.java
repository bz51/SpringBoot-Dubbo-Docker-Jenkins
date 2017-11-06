package com.gaoxi.enumeration.order;

import com.gaoxi.enumeration.BaseEnum;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 上午11:02
 *
 * @description 支付方式枚举
 */
public enum PayModeEnum implements BaseEnum {

    ALIPAY(1,"支付宝"),
    WECHAT(2,"微信支付"),
    UNIONPAY(3,"银联支付"),
    ;

    private int code;
    private String msg;

    PayModeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
