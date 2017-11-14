package com.gaoxi.order.component.changestate;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.enumeration.order.OrderStateEnum;
import com.gaoxi.order.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/13 上午8:43
 *
 * @description 买家待收货状态
 */
@Component
public class BuyerReceivingChangeStateComponent extends BaseChangeStateComponent {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    protected void preHandle(OrderProcessContext orderProcessContext) {
        super.preHandle(orderProcessContext);

        // 插入物流单号
        insertExpressNo(orderProcessContext);
    }

    /**
     * 插入物流单号
     * @param orderProcessContext
     */
    private void insertExpressNo(OrderProcessContext orderProcessContext) {

        // 获取物流单号
        String expressNo = (String) orderProcessContext.getOrderProcessReq().getReqData();

        // 获取订单ID
        String orderId = orderProcessContext.getOrderProcessReq().getOrderId();

        // 构造插入请求
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setId(orderId);
        ordersEntity.setExpressNo(expressNo);

        // 插入物流单号
        orderDAO.updateOrder(ordersEntity);
    }


    @Override
    public void setTargetOrderState() {
        this.targetOrderState = OrderStateEnum.BUYER_RECEIVING;
    }

}
