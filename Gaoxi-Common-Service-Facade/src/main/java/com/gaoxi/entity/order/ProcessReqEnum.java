package com.gaoxi.entity.order;

import com.gaoxi.enumeration.BaseEnum;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午1:53
 *
 * @description 订单受理请求枚举
 */
public enum ProcessReqEnum implements BaseEnum{
    ;

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
