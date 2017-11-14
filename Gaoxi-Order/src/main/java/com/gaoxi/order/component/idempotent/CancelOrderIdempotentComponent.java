package com.gaoxi.order.component.idempotent;

import com.gaoxi.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/10 下午5:01
 * @description
 */
@Component
public class CancelOrderIdempotentComponent extends BaseIdempotencyComponent {
    @Override
    protected void setAllowStateList() {
        this.allowStateList = Arrays.asList(OrderStateEnum.INIT, OrderStateEnum.BUYER_UNPAID);
    }
}
