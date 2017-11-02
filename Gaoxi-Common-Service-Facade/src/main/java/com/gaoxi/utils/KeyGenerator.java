package com.gaoxi.utils;

import java.util.UUID;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/2 下午4:27
 * @description 主键生成器
 */
public class KeyGenerator {

    /** 主键的长度 */
    private static final int UUID_LENGTH = 10;

    /**
     * 获得指定数目的UUID
     * @return
     */
    public static String getKey(){
        StringBuilder sb = new StringBuilder(UUID_LENGTH);
        for(int i=0;i<UUID_LENGTH;i++){
            sb.append(getUUID());
        }
        return sb.toString();
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    private static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
