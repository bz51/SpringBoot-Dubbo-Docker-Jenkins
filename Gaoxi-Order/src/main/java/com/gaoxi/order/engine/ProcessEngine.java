package com.gaoxi.order.engine;

import com.gaoxi.entity.order.ProcessReqEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.CommonSysException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.order.processor.Processor;
import com.gaoxi.req.order.OrderProcessReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Map<ProcessReqEnum, Processor> processorMap;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 请求受理函数
     * @param orderProcessReq 请求
     */
    public void process(OrderProcessReq orderProcessReq) {
        // 校验参数
        checkParam(orderProcessReq);

        // 获取受理器
        Processor processor = processorMap.get(orderProcessReq.getProcessReqEnum());

        // 受理
        processor.handle(orderProcessReq);
    }

    /**
     * 参数校验
     * @param orderProcessReq 订单受理请求
     */
    private void checkParam(OrderProcessReq orderProcessReq) {
        // 受理请求为空
        if (orderProcessReq == null) {
            throw new CommonSysException(ExpCodeEnum.PROCESSREQ_NULL);
        }

        // orderId为空
        if (orderProcessReq == null) {
            throw new CommonSysException(ExpCodeEnum.PROCESSREQ_ORDERID_NULL);
        }

        // userId为空
        if (orderProcessReq == null) {
            throw new CommonSysException(ExpCodeEnum.PROCESSREQ_USERID_NULL);
        }

        // 受理请求枚举为空
        if (orderProcessReq == null) {
            throw new CommonSysException(ExpCodeEnum.PROCESSREQ_ENUM_NULL);
        }
    }

}
