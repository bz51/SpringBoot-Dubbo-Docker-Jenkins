package com.gaoxi.order.component.idempotent;

import com.gaoxi.enumeration.order.OrderStateEnum;

import java.util.Arrays;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/13 上午8:39
 *
 * @description 确认发货的幂等性检查
 */
public class ConfirmDeliveryIdempotentComponent extends BaseIdempotencyComponent {
    @Override
    protected void setAllowStateList() {
        // 订单状态只有为"买家待收货"才允许确认收货
        this.allowStateList = Arrays.asList(OrderStateEnum.BUYER_PAID);
    }
}
