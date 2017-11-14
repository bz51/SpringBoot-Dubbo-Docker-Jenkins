package com.gaoxi.order.processor;

import com.gaoxi.order.annotation.InjectComponents;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.order.component.checkparam.NoPrivateCheckParamComponent;
import com.gaoxi.order.component.checkstock.CommonCheckStockComponent;
import com.gaoxi.order.component.datatransfer.ProdCountMapTransferComponent;
import com.gaoxi.order.component.datatransfer.ProdIdCountMapTransferComponent;
import com.gaoxi.order.component.idempotent.PayIdempotentComponent;
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
            NoPrivateCheckParamComponent.class,
            // 幂等检查
            PayIdempotentComponent.class,
            // 根据orderId还原prodIdCountMap
            ProdIdCountMapTransferComponent.class,
            // prodIdCountMap——>prodEntityCountMap
            ProdCountMapTransferComponent.class,
            // 库存检查
            CommonCheckStockComponent.class,
            // 支付
            CommonPayComponent.class,
    })
    /** 业务组件列表(当前处理器需要处理的组件列表) */
    protected List<BaseComponent> componentList;

    @Override
    protected void overrideSuperComponentList() {
        super.componentList = this.componentList;
    }
}
