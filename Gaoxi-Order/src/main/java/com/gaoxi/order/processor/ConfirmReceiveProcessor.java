package com.gaoxi.order.processor;

import com.gaoxi.order.annotation.InjectComponents;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.order.component.changestate.BuyerReceivingChangeStateComponent;
import com.gaoxi.order.component.changestate.FinishedChangeStateComponent;
import com.gaoxi.order.component.checkparam.BaseCheckParamComponent;
import com.gaoxi.order.component.checkparam.ConfirmDeliveryCheckParamComponent;
import com.gaoxi.order.component.checkparam.NoPrivateCheckParamComponent;
import com.gaoxi.order.component.idempotent.ConfirmDeliveryIdempotentComponent;
import com.gaoxi.order.component.idempotent.ConfirmReceiveIdemopotentComponent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/13 上午8:49
 * @description
 */
@Component
public class ConfirmReceiveProcessor extends Processor {
    @InjectComponents({
            // 参数校验
            NoPrivateCheckParamComponent.class,
            // 幂等检查(订单状态只有为"买家待收货" 或 "买家已签收 & 买家未确认收货"才允许确认收货)
            ConfirmReceiveIdemopotentComponent.class,
            // 状态流转(已完成)
            FinishedChangeStateComponent.class
    })
    /** 业务组件列表(当前处理器需要处理的组件列表) */
    protected List<BaseComponent> componentList;

    @Override
    protected void overrideSuperComponentList() {
        super.componentList = this.componentList;
    }
}
