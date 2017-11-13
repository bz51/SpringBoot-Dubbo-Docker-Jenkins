package com.gaoxi.order.processor;

import com.gaoxi.order.annotation.InjectComponents;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.order.component.changestate.BuyerReceivingChangeStateComponent;
import com.gaoxi.order.component.changestate.CancelChangeStateComponent;
import com.gaoxi.order.component.checkparam.BaseCheckParamComponent;
import com.gaoxi.order.component.checkparam.ConfirmDeliveryCheckParamComponent;
import com.gaoxi.order.component.idempotent.CancelOrderIdempotentComponent;
import com.gaoxi.order.component.idempotent.ConfirmDeliveryIdempotentComponent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/10 下午5:28
 *
 * @description 买家确认收货
 */
@Component
public class ConfirmDeliveryProcessor extends Processor {

    @InjectComponents({
            // 参数校验(物流单号不能为空)
            ConfirmDeliveryCheckParamComponent.class,
            // 幂等检查(订单状态只有为"买家已付款/买家待发货"才允许发货)
            ConfirmDeliveryIdempotentComponent.class,
            // 状态流转(买家待收货)
            BuyerReceivingChangeStateComponent.class
    })
    /** 业务组件列表(当前处理器需要处理的组件列表) */
    protected List<BaseComponent> componentList;

    @Override
    protected void overrideSuperComponentList() {
        super.componentList = this.componentList;
    }
}
