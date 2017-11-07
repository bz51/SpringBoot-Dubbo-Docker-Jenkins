package com.gaoxi.order.util;

import com.gaoxi.utils.KeyGenerator;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/7 上午9:40
 * @description 订单主键生成器
 */
public class OrderKeyGenerator {

    private static final String ORDER_ID_PREFIX = "ORDER";

    public static String generateOrderKey() {
        return ORDER_ID_PREFIX + KeyGenerator.getKey();
    }

}
