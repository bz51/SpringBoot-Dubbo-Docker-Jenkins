package com.gaoxi.order.component.checkparam;

import com.gaoxi.context.OrderProcessContext;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/10 下午4:29
 *
 * @description 支付操作的参数检查
 */
public class PayCheckParamComponent extends BaseCheckParamComponent {
    @Override
    protected void privateParamCheck(OrderProcessContext orderProcessContext) {
        // 无需检查参数
    }
}
