package com.gaoxi.order.annotation;

import java.lang.annotation.*;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/7 下午4:42
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PackageScan {
    public String value();
}
