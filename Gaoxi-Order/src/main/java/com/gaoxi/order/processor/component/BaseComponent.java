package com.gaoxi.order.processor.component;

import com.gaoxi.req.order.OrderProcessReq;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午2:05
 *
 * @description 业务组件的父类
 */
public abstract class BaseComponent {

    public abstract void handle(OrderProcessReq orderProcessReq);

}
