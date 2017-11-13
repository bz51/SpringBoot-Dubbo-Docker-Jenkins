package com.gaoxi.order.component.checkparam;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.enumeration.order.ProcessReqEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.order.component.BaseComponent;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/7 下午1:13
 *
 * @description 参数校验的父类
 */
public abstract class BaseCheckParamComponent extends BaseComponent {

    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);

        // 通用参数校验
        commonParamCheck(orderProcessContext);

        // 子校验组件特有的参数校验
        privateParamCheck(orderProcessContext);

        afterHandle(orderProcessContext);
    }

    /**
     * 子校验组件特有的参数校验
     * @param orderProcessContext 订单受理上下文
     */
    protected abstract void privateParamCheck(OrderProcessContext orderProcessContext);


    /**
     * 校验组件通用的参数校验
     * @param orderProcessContext 订单受理上下文
     */
    private void commonParamCheck(OrderProcessContext orderProcessContext) {
        // context不能为空
        checkParam(orderProcessContext, ExpCodeEnum.PROCESSCONTEXT_NULL);

        // OrderProcessReq不能为空
        checkParam(orderProcessContext.getOrderProcessReq(), ExpCodeEnum.PROCESSREQ_NULL);

        // 受理人ID不能为空
        checkParam(orderProcessContext.getOrderProcessReq().getUserId(), ExpCodeEnum.USERID_NULL);

        // ProcessReqEnum不能为空
        checkParam(orderProcessContext.getOrderProcessReq().getProcessReqEnum(), ExpCodeEnum.PROCESSREQ_ENUM_NULL);

        // orderId不能为空(下单请求除外)
        if (orderProcessContext.getOrderProcessReq().getProcessReqEnum() != ProcessReqEnum.PlaceOrder) {
            checkParam(orderProcessContext.getOrderProcessReq().getOrderId(), ExpCodeEnum.PROCESSREQ_ORDERID_NULL);
        }
    }


    /**
     * 参数校验
     * @param param 待校验参数
     * @param expCodeEnum 异常错误码
     */
    protected <T> void checkParam(T param, ExpCodeEnum expCodeEnum) {
        if (param == null) {
            throw new CommonBizException(expCodeEnum);
        }

        if (param instanceof String && StringUtils.isEmpty((String) param)) {
            throw new CommonBizException(expCodeEnum);
        }

        if (param instanceof Collection && CollectionUtils.isEmpty((Collection<?>) param)) {
            throw new CommonBizException(expCodeEnum);
        }
    }
}
