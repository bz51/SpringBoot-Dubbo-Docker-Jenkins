package com.gaoxi.enumeration;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午2:40
 * @description 枚举的顶层父类
 */
public interface BaseEnum {

    /**
     * 获取枚举码
     */
    int getCode();

    /**
     * 获取枚举描述
     */
    String getMsg();
}
