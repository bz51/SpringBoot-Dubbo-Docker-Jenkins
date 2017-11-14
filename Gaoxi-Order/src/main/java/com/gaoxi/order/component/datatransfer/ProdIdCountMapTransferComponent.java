package com.gaoxi.order.component.datatransfer;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.entity.order.ProductOrderEntity;
import com.gaoxi.enumeration.order.PayModeEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.order.dao.OrderDAO;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderQueryReq;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/14 下午8:29
 *
 * @description 根据orderId，生成prodIdCountMap
 */
@Component
public class ProdIdCountMapTransferComponent extends BaseDataTransferComponent {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    protected void transfer(OrderProcessContext orderProcessContext) {
        // 获取订单ID 和 购买者ID
        String orderId = orderProcessContext.getOrderProcessReq().getOrderId();
        String buyerId = orderProcessContext.getOrderProcessReq().getUserId();

        // 构造查询请求
        OrderQueryReq orderQueryReq = buildOrderQueryReq(orderId, buyerId);

        // 查询该笔订单
        OrdersEntity ordersEntity = query(orderQueryReq);

        // 构建prodIdCountMap
        Map<String, Integer> prodIdCountMap = buildProdIdCountMap(ordersEntity);

        // 装入context
        setIntoContext(prodIdCountMap, ordersEntity.getPayModeEnum(), orderProcessContext);
    }


    /**
     * 订单查询
     * @param orderQueryReq 订单查询请求
     * @return 订单结果
     */
    private OrdersEntity query(OrderQueryReq orderQueryReq) {
        List<OrdersEntity> ordersEntityList = orderDAO.findOrders(orderQueryReq);
        if (CollectionUtils.isEmpty(ordersEntityList)) {
            throw new CommonBizException(ExpCodeEnum.ORDER_NULL);
        }

        return ordersEntityList.get(0);
    }

    /**
     * 构造订单查询请求
     * @param orderId 订单ID
     * @param buyerId 购买者ID
     * @return 订单查询请求
     */
    private OrderQueryReq buildOrderQueryReq(String orderId, String buyerId) {
        OrderQueryReq orderQueryReq = new OrderQueryReq();
        orderQueryReq.setId(orderId);
        orderQueryReq.setBuyerId(buyerId);
        return orderQueryReq;
    }


    /**
     * 构建prodIdCountMap
     * @param ordersEntity 订单详情
     * @return prodIdCountMap
     */
    private Map<String, Integer> buildProdIdCountMap(OrdersEntity ordersEntity) {
        // 初始化结果集
        Map<String, Integer> prodIdCountMap = Maps.newHashMap();

        // 获取产品列表
        List<ProductOrderEntity> productOrderList = ordersEntity.getProductOrderList();

        // 构造结果集
        for (ProductOrderEntity productOrderEntity : productOrderList) {
            // 产品ID
            String prodId = productOrderEntity.getProductEntity().getId();
            // 购买数量
            Integer count = productOrderEntity.getCount();

            prodIdCountMap.put(prodId, count);
        }

        return prodIdCountMap;
    }


    /**
     * 将prodIdCountMap装入Context
     * @param prodIdCountMap
     * @param payModeEnum
     * @param orderProcessContext
     */
    private void setIntoContext(Map<String, Integer> prodIdCountMap, PayModeEnum payModeEnum, OrderProcessContext orderProcessContext) {

        // 构造 订单插入请求
        OrderInsertReq orderInsertReq = new OrderInsertReq();

        // 插入prodIdCountMap
        orderInsertReq.setProdIdCountMap(prodIdCountMap);

        // 插入支付方式
        orderInsertReq.setPayModeCode(payModeEnum.getCode());

        orderProcessContext.getOrderProcessReq().setReqData(orderInsertReq);
    }
}
