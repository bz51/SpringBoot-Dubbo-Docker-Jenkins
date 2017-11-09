package com.gaoxi.order.component.checkparam;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.req.order.OrderProcessReq;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/7 下午2:23
 * @description 下单参数校验
 */
@Component
public class PlaceOrderCheckParamComponent extends BaseCheckParamComponent {

    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        // TODO 下单的参数校验
    }
}
