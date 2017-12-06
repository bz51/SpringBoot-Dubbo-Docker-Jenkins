package com.gaoxi.controller.order;

import com.gaoxi.annotation.Login;
import com.gaoxi.annotation.Permission;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderQueryReq;
import com.gaoxi.rsp.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:28
 */
public interface OrderController {

    /**
     * 订单查询(供买家查询)
     * @param orderQueryReq 订单查询请求
     * @param httpReq HTTP请求
     * @return 订单查询结果
     */
    @GetMapping("/buyerOrders")
    @Login
    Result<List<OrdersEntity>> findOrdersForBuyer(OrderQueryReq orderQueryReq, HttpServletRequest httpReq);

    /**
     * 订单查询(供卖家查询)
     * @param orderQueryReq 订单查询请求
     * @param httpReq HTTP请求
     * @return 订单查询结果
     */
    @GetMapping("/sellerOrders")
    @Login
    Result<List<OrdersEntity>> findOrdersForSeller(OrderQueryReq orderQueryReq, HttpServletRequest httpReq);

    /**
     * 订单查询(供管理员查询)
     * @param orderQueryReq 订单查询请求
     * @param httpReq HTTP请求
     * @return 订单查询结果
     */
    @GetMapping("/adminOrders")
    @Login
    Result<List<OrdersEntity>> findOrdersForAdmin(OrderQueryReq orderQueryReq, HttpServletRequest httpReq);

    /**
     * 下单(包含支付)
     * @param orderInsertReq 下单请求
     * @param httpReq HTTP请求
     * @return 返回支付页面的HTML代码
     */
    @PostMapping("/placeOrder")
    @Login
    Result<String> placeOrder(OrderInsertReq orderInsertReq, HttpServletRequest httpReq);

    /**
     * 支付(仅供状态为"待支付"的订单使用)
     * @param orderId 待支付订单ID
     * @param httpReq HTTP请求
     * @return 返回支付页面的HTML代码
     */
    @PostMapping("/pay")
    @Login
    Result<String> pay(String orderId, HttpServletRequest httpReq);

    /**
     * 取消订单(仅供状态为"待支付"的订单使用)
     * @param orderId 订单ID
     * @return 执行结果
     */
    @DeleteMapping("/order")
    @Login
    Result cancelOrder(String orderId, HttpServletRequest httpReq);

    /**
     * 卖家确认发货
     * @param orderId 订单ID
     * @param expressNo 物流单号
     * @return 是否执行成功
     */
    @PutMapping("/delivery")
    @Login
    Result confirmDelivery(String orderId, String expressNo, HttpServletRequest httpReq);

    /**
     * 买家确认收货
     * @param orderId 订单ID
     * @return 是否执行成功
     */
    @PutMapping("/receive")
    @Login
    Result confirmReceive(String orderId, HttpServletRequest httpReq);


}
