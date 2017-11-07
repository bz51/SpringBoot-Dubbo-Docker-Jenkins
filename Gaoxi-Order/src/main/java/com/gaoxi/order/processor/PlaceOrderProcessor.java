package com.gaoxi.order.processor;

import com.gaoxi.order.annotation.InjectComponents;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.order.component.changestate.BaseChangeStateComponent;
import com.gaoxi.order.component.checkparam.BaseCheckParamComponent;
import com.gaoxi.order.component.checkparam.PlaceOrderCheckParamComponent;
import com.gaoxi.order.component.checkstock.CheckStockComponent;
import com.gaoxi.order.component.idempotent.PlaceOrderIdempotentComponent;
import com.gaoxi.order.component.pay.BasePayComponent;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午1:59
 *
 * @description 下单受理器
 */
public class PlaceOrderProcessor extends Processor {

    @InjectComponents({
            // 参数校验
            PlaceOrderCheckParamComponent.class,
            // 幂等检查
            PlaceOrderIdempotentComponent.class,
            // 库存检查
            CheckStockComponent.class,
            // 支付
            BasePayComponent.class,
            // 修改订单状态
            BaseChangeStateComponent.class
    })
    /** 业务组件列表(当前处理器需要处理的组件列表) */
    protected List<BaseComponent> componentList;

}
