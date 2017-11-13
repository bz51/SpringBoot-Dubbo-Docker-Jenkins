package com.gaoxi.order.component.pay;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.order.component.BaseComponent;
import org.springframework.stereotype.Component;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/9 下午3:30
 * @description
 */
@Component
public class UnionPayComponent extends BaseComponent {
    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        // TODO 银联支付

        afterHandle(orderProcessContext);
    }
}
