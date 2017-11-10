package com.gaoxi.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.enumeration.order.ProcessReqEnum;
import com.gaoxi.facade.order.OrderService;
import com.gaoxi.order.dao.OrderDAO;
import com.gaoxi.order.engine.ProcessEngine;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderProcessReq;
import com.gaoxi.req.order.OrderQueryReq;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/10 上午9:39
 * @description
 */
@org.springframework.stereotype.Service
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    /** 订单受理引擎 */
    private ProcessEngine processEngine;

    @Override
    public List<OrdersEntity> findOrders(OrderQueryReq orderQueryReq) {
        return orderDAO.findOrders(orderQueryReq);
    }

    @Override
    public String placeOrder(OrderInsertReq orderInsertReq) {
        // 构造受理上下文
        OrderProcessContext<String> context = buildContext(orderInsertReq);

        // 受理下单请求
        processEngine.process(context);

        // 获取结果
        return context.getOrderProcessRsp();
    }

    /**
     * 构造订单受理上下文
     * @param orderInsertReq 订单插入请求
     * @return 订单受理上下文
     */
    private OrderProcessContext<String> buildContext(OrderInsertReq orderInsertReq) {
        OrderProcessContext context = new OrderProcessContext();

        // 受理请求
        OrderProcessReq<OrderInsertReq> req = new OrderProcessReq<>();
        req.setProcessReqEnum(ProcessReqEnum.PlaceOrder);
        req.setUserId(orderInsertReq.getUserId());
        req.setReqData(orderInsertReq);

        context.setOrderProcessReq(req);
        return context;
    }

    @Override
    public String pay(String orderId, String userId) {
        // 构造受理上下文
        OrderProcessContext<String> context = buildContext(orderId, userId, ProcessReqEnum.Pay);

        // 受理下单请求
        processEngine.process(context);

        // 获取结果
        return context.getOrderProcessRsp();
    }

    private OrderProcessContext<String> buildContext(String orderId, String userId, ProcessReqEnum processReqEnum) {
        OrderProcessContext context = new OrderProcessContext();

        // 受理请求
        OrderProcessReq req = new OrderProcessReq();
        req.setProcessReqEnum(processReqEnum);
        req.setUserId(userId);
        req.setOrderId(orderId);

        context.setOrderProcessReq(req);
        return context;
    }

    @Override
    public void cancelOrder(String orderId, String userId) {
        // 构造受理上下文
        OrderProcessContext<String> context = buildContext(orderId, userId, ProcessReqEnum.CancelOrder);

        // 受理下单请求
        processEngine.process(context);
    }

    @Override
    public void confirmDelivery(String orderId, String expressNo, String userId) {
        // 构造受理上下文
        OrderProcessContext<String> context = buildContext(orderId, userId, ProcessReqEnum.ConfirmDelivery);

        // 受理下单请求
        processEngine.process(context);
    }

    @Override
    public void confirmReceive(String orderId, String userId) {
        // 构造受理上下文
        OrderProcessContext<String> context = buildContext(orderId, userId, ProcessReqEnum.ConfirmReceive);

        // 受理下单请求
        processEngine.process(context);
    }
}
