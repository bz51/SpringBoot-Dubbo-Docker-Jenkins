package com.gaoxi.order.component;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.req.order.OrderProcessReq;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午2:05
 *
 * @description 业务组件的父类
 */
public abstract class BaseComponent {

    protected void preHandle(OrderProcessContext orderProcessContext) {

    }

    public abstract void handle(OrderProcessContext orderProcessContext);

    protected void afterHandle(OrderProcessContext orderProcessContext) {

    }

}
