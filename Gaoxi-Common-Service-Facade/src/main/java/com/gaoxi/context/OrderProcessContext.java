package com.gaoxi.context;

import com.gaoxi.req.order.OrderProcessReq;
import com.gaoxi.rsp.Result;

import java.io.Serializable;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午2:39
 *
 * @description 订单受理上下文
 */
public class OrderProcessContext <T> implements Serializable {

    /** 是否终止下面的流程 */
    private boolean isStop = false;

    /** 订单受理请求 */
    private OrderProcessReq orderProcessReq;

    /** 订单受理结果 */
    private T orderProcessRsp;

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public OrderProcessReq getOrderProcessReq() {
        return orderProcessReq;
    }

    public void setOrderProcessReq(OrderProcessReq orderProcessReq) {
        this.orderProcessReq = orderProcessReq;
    }

    public T getOrderProcessRsp() {
        return orderProcessRsp;
    }

    public void setOrderProcessRsp(T orderProcessRsp) {
        this.orderProcessRsp = orderProcessRsp;
    }

    @Override
    public String toString() {
        return "OrderProcessContext{" +
                "isStop=" + isStop +
                ", orderProcessReq=" + orderProcessReq +
                ", orderProcessRsp=" + orderProcessRsp +
                '}';
    }
}
