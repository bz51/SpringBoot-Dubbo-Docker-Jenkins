package com.gaoxi.order.annotation;

import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.order.processor.Processor;

import java.lang.annotation.*;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/9 下午2:43
 *
 * @description 向ProcessEngine中注入所有的Processor
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectProcessors {
    public Class<? extends Processor>[] value();
}
