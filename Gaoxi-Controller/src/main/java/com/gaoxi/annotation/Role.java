package com.gaoxi.annotation;

import java.lang.annotation.*;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/27 下午10:28
 * @description 本注解用在Controller层的接口上，表示访问该接口所需的角色
 */
// 本注解只能用在方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Role {
    public String value();
}
