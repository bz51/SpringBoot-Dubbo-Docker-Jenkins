package com.gaoxi.order.component.changestate;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.order.OrderStateTimeEntity;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.enumeration.order.OrderStateEnum;
import com.gaoxi.exception.CommonSysException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.order.dao.OrderDAO;
import com.gaoxi.order.component.BaseComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/7 下午1:21
 *
 * @description 订单状态流转组件
 */
public abstract class BaseChangeStateComponent extends BaseComponent {

    /** 是否终止 */
    protected boolean isStop = false;

    /** 订单目标状态 */
    protected OrderStateEnum targetOrderState;

    @Autowired
    private OrderDAO orderDAO;


    /** 状态改变前的前置处理 */
    @Override
    protected void preHandle(OrderProcessContext orderProcessContext) {

    }

    /** 状态改变后的后置处理 */
    @Override
    protected void afterHandle(OrderProcessContext orderProcessContext) {

    }


    /**
     * 将指定订单的状态更新成指定值
     * @param orderProcessContext 订单受理上下文
     * @param targetOrderState 订单目标状态
     */
    private void changeState(OrderProcessContext orderProcessContext, OrderStateEnum targetOrderState) {

        // 目标状态为空
        checkParam(targetOrderState);

        // 获取订单ID
        String orderId = orderProcessContext.getOrderProcessReq().getOrderId();

        // 更新订单状态
        updateOrderState(orderId, targetOrderState);
    }

    /**
     * 更新订单的状态
     * @param orderId 订单ID
     * @param targetOrderState 订单状态
     */
    private void updateOrderState(String orderId, OrderStateEnum targetOrderState) {
        // 构造OrderStateTimeEntity
        OrderStateTimeEntity orderStateTimeEntity = buildOrderStateTimeEntity(orderId, targetOrderState);

        // 删除该订单的该条状态
        orderDAO.deleteOrderStateTime(orderStateTimeEntity);

        // 插入该订单的该条状态
        orderDAO.insertOrderStateTime(orderStateTimeEntity);

        // 更新order表中的订单状态
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setId(orderId);
        ordersEntity.setOrderStateEnum(targetOrderState);
        orderDAO.updateOrder(ordersEntity);
    }

    /**
     * 构造用于更新订单状态的buildOrderStateTimeEntity
     * @param orderId 订单ID
     * @param targetOrderState 订单状态
     * @return OrderStateTimeEntity
     */
    private OrderStateTimeEntity buildOrderStateTimeEntity(String orderId, OrderStateEnum targetOrderState) {
        OrderStateTimeEntity orderStateTimeEntity = new OrderStateTimeEntity();
        orderStateTimeEntity.setOrderId(orderId);
        orderStateTimeEntity.setOrderStateEnum(targetOrderState);
        orderStateTimeEntity.setTime(new Timestamp(System.currentTimeMillis()));
        return orderStateTimeEntity;
    }

    private void checkParam(OrderStateEnum targetOrderState) {
        if (targetOrderState == null) {
            throw new CommonSysException(ExpCodeEnum.TARGETSTATE_NULL);
        }
    }

    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        // 设置目标状态
        setTargetOrderState();

        // 前置处理
        this.preHandle(orderProcessContext);
        if (this.isStop) {
            return;
        }

        // 改变状态
        this.changeState(orderProcessContext, this.targetOrderState);
        if (this.isStop) {
            return;
        }

        // 后置处理
        this.afterHandle(orderProcessContext);
    }

    public abstract void setTargetOrderState();
}
