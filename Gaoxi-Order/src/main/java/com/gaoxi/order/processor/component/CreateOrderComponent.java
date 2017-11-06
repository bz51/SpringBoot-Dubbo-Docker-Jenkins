package com.gaoxi.order.processor.component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.order.dao.OrderDAO;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderProcessReq;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午3:56
 *
 * @description 创建订单组件
 */
public class CreateOrderComponent extends BaseComponent {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public void handle(OrderProcessReq orderProcessReq) {

        // 获取订单创建请求
        OrderInsertReq orderInsertReq = getOrderInsertReq(orderProcessReq);

        // 参数校验
        checkParam(orderInsertReq);

        // OrdersEntity
        OrdersEntity ordersEntity = buildUserEntity(orderInsertReq);

        // 创建订单
        createOrder(orderInsertReq);


    }

    /**
     * 构造UserEntity
     * @param orderInsertReq 订单创建请求
     * @return OrdersEntity
     */
    private OrdersEntity buildUserEntity(OrderInsertReq orderInsertReq) {
        return null;
    }

    /**
     * 创建订单
     * @param orderInsertReq 订单创建请求
     */
    private void createOrder(OrderInsertReq orderInsertReq) {

    }

    /**
     * 参数校验
     * @param orderInsertReq 订单创建请求
     */
    private void checkParam(OrderInsertReq orderInsertReq) {
        // 买家ID不能为空
        if (StringUtils.isEmpty(orderInsertReq.getUserId())) {
            throw new CommonBizException(ExpCodeEnum.USERID_NULL);
        }

        // 支付方式不能为空
//        if (orderInsertReq.getPayModeCode() != null) {
//            throw new CommonBizException(ExpCodeEnum._NULL);
//        }

        // TODO 还有很多参数校验尚未写完
    }

    /**
     * 获取订单创建请求
     * @param orderProcessReq 订单受理请求
     * @return 订单创建请求
     */
    private OrderInsertReq getOrderInsertReq(OrderProcessReq orderProcessReq) {
        Object orderInsertReq = orderProcessReq.getT();
        if (orderInsertReq == null) {
            throw new CommonBizException(ExpCodeEnum.ORDER_INSERT_REQ_NULL);
        }

        return (OrderInsertReq) orderInsertReq;
    }

}
