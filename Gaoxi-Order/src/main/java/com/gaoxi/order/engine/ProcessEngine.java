package com.gaoxi.order.engine;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.enumeration.order.ProcessReqEnum;
import com.gaoxi.exception.CommonSysException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.order.annotation.InjectProcessors;
import com.gaoxi.order.processor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午1:31
 *
 * @description 订单受理引擎
 */
@Component
public class ProcessEngine {

    /** 受理器Map */
    @InjectProcessors({
            PlaceOrderProcessor.class,
            PayProcessor.class,
            ConfirmDeliveryProcessor.class,
            ConfirmReceiveProcessor.class,
            CancelOrderProcessor.class
    })
    private Map<ProcessReqEnum, Processor> processorMap;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    /**
     * 请求受理函数
     * @param orderProcessContext 订单受理上下文(包含：req+rsp)
     */
    public void process(OrderProcessContext orderProcessContext) {
        // 校验参数
        checkParam(orderProcessContext);

        // 获取受理器
        Processor processor = processorMap.get(orderProcessContext.getOrderProcessReq().getProcessReqEnum());
        System.out.println(processorMap);

        // 受理
        processor.handle(orderProcessContext);
    }

    /**
     * 参数校验
     * @param orderProcessContext 订单受理上下文
     */
    private void checkParam(OrderProcessContext orderProcessContext) {
        // 受理上下文为空
        if (orderProcessContext == null) {
            throw new CommonSysException(ExpCodeEnum.PROCESSCONTEXT_NULL);
        }

        // 受理请求为空
        if (orderProcessContext.getOrderProcessReq() == null) {
            throw new CommonSysException(ExpCodeEnum.PROCESSREQ_NULL);
        }

        // 受理请求枚举为空
        if (orderProcessContext.getOrderProcessReq().getProcessReqEnum() == null) {
            throw new CommonSysException(ExpCodeEnum.PROCESSREQ_ENUM_NULL);
        }

        // orderId为空(除下单外)
        if (orderProcessContext.getOrderProcessReq().getOrderId() == null
                && orderProcessContext.getOrderProcessReq().getProcessReqEnum() != ProcessReqEnum.PlaceOrder) {
            throw new CommonSysException(ExpCodeEnum.PROCESSREQ_ORDERID_NULL);
        }

        // userId为空
        if (orderProcessContext.getOrderProcessReq().getUserId() == null) {
            throw new CommonSysException(ExpCodeEnum.PROCESSREQ_USERID_NULL);
        }
    }



}
