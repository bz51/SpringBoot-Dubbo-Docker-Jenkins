package com.gaoxi.order.component.idempotent;

import com.gaoxi.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午3:50
 *
 * @description 下单幂等性检查组件
 */
@Component
public class PlaceOrderIdempotentComponent extends BaseIdempotencyComponent {

    @Override
    protected void setAllowStateList() {
        // 当前订单状态为 INIT 才允许下单
        // TODO 下单是否需要幂等性检查？？？
        this.allowStateList = Arrays.asList(OrderStateEnum.INIT);
    }

}
