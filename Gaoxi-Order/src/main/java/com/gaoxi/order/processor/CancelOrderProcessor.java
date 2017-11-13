package com.gaoxi.order.processor;

import com.gaoxi.order.annotation.InjectComponents;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.order.component.changestate.CancelChangeStateComponent;
import com.gaoxi.order.component.checkparam.BaseCheckParamComponent;
import com.gaoxi.order.component.checkparam.PlaceOrderCheckParamComponent;
import com.gaoxi.order.component.checkstock.CheckStockComponent;
import com.gaoxi.order.component.idempotent.CancelOrderIdempotentComponent;
import com.gaoxi.order.component.idempotent.PlaceOrderIdempotentComponent;
import com.gaoxi.order.component.pay.CommonPayComponent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/10 下午4:59
 *
 * @description 取消订单
 */
@Component
public class CancelOrderProcessor extends Processor {

    @InjectComponents({
            // 参数校验
            BaseCheckParamComponent.class,
            // 幂等检查
            CancelOrderIdempotentComponent.class,
            // 状态流转
            CancelChangeStateComponent.class
    })
    /** 业务组件列表(当前处理器需要处理的组件列表) */
    protected List<BaseComponent> componentList;
}
