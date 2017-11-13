package com.gaoxi.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.order.OrderService;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderQueryReq;
import com.gaoxi.rsp.Result;
import com.gaoxi.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:28
 */
@RestController
public class OrderControllerImpl implements OrderController {

    @Reference
    private OrderService orderService;

    @Autowired
    private UserUtil userUtil;


    @Override
    public Result<List<OrdersEntity>> findOrdersForBuyer(OrderQueryReq orderQueryReq, HttpServletRequest httpReq) {
        // 获取买家ID
        String buyerId = getUserId(httpReq);

        // 查询
        List<OrdersEntity> ordersEntityList = orderService.findOrdersForBuyer(orderQueryReq, buyerId);

        // 查询成功
        return Result.newSuccessResult(ordersEntityList);
    }


    @Override
    public Result<List<OrdersEntity>> findOrdersForSeller(OrderQueryReq orderQueryReq, HttpServletRequest httpReq) {
        // 获取卖家ID
        String sellerId = getUserId(httpReq);

        // 查询
        List<OrdersEntity> ordersEntityList = orderService.findOrdersForSeller(orderQueryReq, sellerId);

        // 查询成功
        return Result.newSuccessResult(ordersEntityList);
    }

    @Override
    public Result<String> placeOrder(OrderInsertReq orderInsertReq, HttpServletRequest httpReq) {
        // 获取买家ID
        String buyerId = getUserId(httpReq);

        // 下单
        String html = orderService.placeOrder(orderInsertReq, buyerId);

        // 成功
        return Result.newSuccessResult(html);
    }

    @Override
    public Result<String> pay(String orderId, HttpServletRequest httpReq) {
        // 获取买家ID
        String buyerId = getUserId(httpReq);

        // 支付
        String html = orderService.pay(orderId, buyerId);

        // 成功
        return Result.newSuccessResult(html);
    }

    @Override
    public Result cancelOrder(String orderId, HttpServletRequest httpReq) {
        // 获取买家ID
        String buyerId = getUserId(httpReq);

        // 取消订单
        orderService.cancelOrder(orderId, buyerId);

        // 成功
        return Result.newSuccessResult();
    }

    @Override
    public Result confirmDelivery(String orderId, String expressNo, HttpServletRequest httpReq) {
        // 获取卖家ID
        String sellerId = getUserId(httpReq);

        // 确认收货
        orderService.confirmDelivery(orderId, expressNo, sellerId);

        // 成功
        return Result.newSuccessResult();
    }

    @Override
    public Result confirmReceive(String orderId, HttpServletRequest httpReq) {
        // 获取买家ID
        String buyerId = getUserId(httpReq);

        // 确认收货
        orderService.confirmReceive(orderId, buyerId);

        // 成功
        return Result.newSuccessResult();
    }







    /**
     * 获取用户ID
     * @param httpReq HTTP请求
     * @return 用户ID
     */
    private String getUserId(HttpServletRequest httpReq) {
        UserEntity userEntity = userUtil.getUser(httpReq);
        if (userEntity == null) {
            throw new CommonBizException(ExpCodeEnum.UNLOGIN);
        }

        return userEntity.getId();
    }
}
