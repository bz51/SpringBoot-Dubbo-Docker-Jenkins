package com.gaoxi.req.order;

import com.gaoxi.entity.order.ProcessReqEnum;
import com.gaoxi.req.AbsReq;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午2:39
 *
 * @description 订单受理请求
 */
public class OrderProcessReq <T> extends AbsReq {

    /** 受理人ID */
    private String userId;

    /** 订单ID */
    private String orderId;

    /** 订单受理请求 */
    private ProcessReqEnum processReqEnum;

    /** 额外的参数 */
    private T t;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ProcessReqEnum getProcessReqEnum() {
        return processReqEnum;
    }

    public void setProcessReqEnum(ProcessReqEnum processReqEnum) {
        this.processReqEnum = processReqEnum;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "OrderProcessReq{" +
                "userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", processReqEnum=" + processReqEnum +
                ", t=" + t +
                '}';
    }
}
