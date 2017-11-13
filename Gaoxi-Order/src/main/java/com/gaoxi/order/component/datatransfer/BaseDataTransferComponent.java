package com.gaoxi.order.component.datatransfer;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.order.component.BaseComponent;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/13 下午2:33
 *
 * @description 数据转化组件的父类
 */
public abstract class BaseDataTransferComponent extends BaseComponent {
    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        transfer(orderProcessContext);
        afterHandle(orderProcessContext);
    }

    /**
     * 将OrderProcessContext中的数据进行转化
     * @param orderProcessContext 订单受理上下文
     */
    protected abstract void transfer(OrderProcessContext orderProcessContext);
}
