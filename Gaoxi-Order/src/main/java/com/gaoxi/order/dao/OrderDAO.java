package com.gaoxi.order.dao;

import com.gaoxi.entity.order.OrderStateTimeEntity;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.req.order.OrderQueryReq;
import com.gaoxi.req.order.OrderUpdateReq;
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

    /**
     * 更新订单
     * @param ordersEntity 订单更新请求
     */
    void updateOrder(OrdersEntity ordersEntity);

    /**
     * 删除订单某一状态的时间
     * @param orderStateTimeEntity 订单状态-时间的对应关系
     */
    void deleteOrderStateTime(OrderStateTimeEntity orderStateTimeEntity);

    /**
     * 插入订单某一状态的时间
     * @param orderStateTimeEntity 订单状态-时间的对应关系
     */
    void insertOrderStateTime(OrderStateTimeEntity orderStateTimeEntity);

}
