package com.gaoxi.order.processor;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.exception.CommonSysException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.req.order.OrderProcessReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午1:33
 *
 * @description 订单状态处理器
 */
public abstract class Processor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** 业务组件列表(当前处理器需要处理的组件列表) */
    protected List<BaseComponent> componentList;



    /**
     * 处理函数
     * @param orderProcessContext
     */
    public void handle(OrderProcessContext orderProcessContext) {
        overrideSuperComponentList();

        // componentList为空
        if (CollectionUtils.isEmpty(componentList)) {
            logger.error(this.getClass().getSimpleName() + "中componentList为空！");
            throw new CommonSysException(ExpCodeEnum.COMPONENT_NULL);
        }

        // 依次执行所有业务组件
        for (BaseComponent component : componentList) {
            component.handle(orderProcessContext);
            // 终止
            if (orderProcessContext.isStop()) {
                break;
            }
        }
    }

    protected abstract void overrideSuperComponentList();

}
