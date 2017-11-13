package com.gaoxi.order.component.changestate;

import com.gaoxi.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/13 上午8:55
 * @description
 */
@Component
public class FinishedChangeStateComponent extends BaseChangeStateComponent {
    @Override
    public void setTargetOrderState() {
        this.targetOrderState = OrderStateEnum.FINISHED;
    }
}