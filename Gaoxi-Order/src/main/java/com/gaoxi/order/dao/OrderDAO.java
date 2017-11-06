package com.gaoxi.order.dao;

import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.req.order.OrderQueryReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午3:14
 * @description
 */
@Mapper
public interface OrderDAO {

    /**
     * 查询订单
     * @param orderQueryReq 订单查询请求
     * @return 订单查询结果
     */
    List<OrdersEntity> findOrders(OrderQueryReq orderQueryReq);

}
