package com.gaoxi.order.processor;

import com.gaoxi.enumeration.order.ProcessReqEnum;
import com.gaoxi.order.annotation.InjectComponents;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.order.component.checkparam.PayCheckParamComponent;
import com.gaoxi.order.component.checkparam.PlaceOrderCheckParamComponent;
import com.gaoxi.order.component.checkstock.CheckStockComponent;
import com.gaoxi.order.component.checkstock.PayCheckStockComponent;
import com.gaoxi.order.component.idempotent.PayIdempotentComponent;
import com.gaoxi.order.component.idempotent.PlaceOrderIdempotentComponent;
import com.gaoxi.order.component.pay.CommonPayComponent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/10 下午4:14
 *
 * @description 支付受理器
 */
@Component
public class PayProcessor extends Processor {

    @InjectComponents({
            // 参数校验
            PayCheckParamComponent.class,
            // 幂等检查
            PayIdempotentComponent.class,
            // 库存检查
            PayCheckStockComponent.class,
            // 支付
            CommonPayComponent.class,
    })
    /** 业务组件列表(当前处理器需要处理的组件列表) */
    protected List<BaseComponent> componentList;
}
