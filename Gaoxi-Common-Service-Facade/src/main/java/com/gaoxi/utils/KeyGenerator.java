package com.gaoxi.utils;

import java.util.UUID;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/2 下午4:27
 * @description 主键生成器
 */
public class KeyGenerator {

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getKey(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
