package com.gaoxi.controller.order;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.dubbo.common.utils.StringUtils;
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
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:28
 */
@RestController
public class OrderControllerImpl implements OrderController {

    @Reference(version = "1.0.0")
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
    public Result<List<OrdersEntity>> findOrdersForAdmin(OrderQueryReq orderQueryReq, HttpServletRequest httpReq) {
        // 查询
        List<OrdersEntity> ordersEntityList = orderService.findOrdersForAdmin(orderQueryReq);

        // 查询成功
        return Result.newSuccessResult(ordersEntityList);
    }

    @Override
    public Result<String> placeOrder(OrderInsertReq orderInsertReq, HttpServletRequest httpReq) {

        // 将prodIdCountJson——>prodIdCountMap
        transferProdIdCountJson(orderInsertReq);

        // 获取买家ID
        String buyerId = getUserId(httpReq);

        // 下单
        String html = orderService.placeOrder(orderInsertReq, buyerId);

        // 成功
        return Result.newSuccessResult(html);
    }

    private void transferProdIdCountJson(OrderInsertReq orderInsertReq) {
        if (orderInsertReq != null && StringUtils.isNotEmpty(orderInsertReq.getProdIdCountJson())) {
            try {
                Map<String, Long> prodIdCountMapPre = JSON.parse(orderInsertReq.getProdIdCountJson(), Map.class);
                Map<String, Integer> prodIdCountMap = Maps.newHashMap();
                if (prodIdCountMapPre.size() > 0) {
                    for (String key : prodIdCountMapPre.keySet()) {
                        prodIdCountMap.put(key, (Integer) prodIdCountMapPre.get(key).intValue());
                    }
                }
                orderInsertReq.setProdIdCountMap(prodIdCountMap);
            } catch (ParseException e) {
                throw new CommonBizException(ExpCodeEnum.JSONERROR);
            }
        }

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
