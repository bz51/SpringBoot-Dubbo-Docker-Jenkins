package com.gaoxi.enumeration.order;

import com.gaoxi.enumeration.BaseEnum;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午1:53
 *
 * @description 订单受理请求枚举
 * 特别注意：msg必须和Processor的类名保持一致！！！
 */
public enum ProcessReqEnum implements BaseEnum{

    PlaceOrder(1, "PlaceOrderProcessor"),
    Pay(2, "PayProcessor"),
    CancelOrder(3, "CancelOrderProcessor"),
    ConfirmDelivery(4, "ConfirmDeliveryProcessor"),
    ConfirmReceive(5, "ConfirmReceiveProcessor");

    private int code;
    private String msg;

    ProcessReqEnum(int code, String msg) {
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
