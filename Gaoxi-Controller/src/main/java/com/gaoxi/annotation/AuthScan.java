package com.gaoxi.annotation;

import java.lang.annotation.*;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/27 下午10:28
 * @description 本注解用于指定扫描用户鉴权相关注解所在的包
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthScan {
    public String value();
}
