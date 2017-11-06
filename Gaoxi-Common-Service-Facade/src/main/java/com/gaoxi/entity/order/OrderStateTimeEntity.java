package com.gaoxi.entity.order;

import com.gaoxi.enumeration.order.OrderStateEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午1:27
 *
 * @description 订单到达各个状态的时间
 */
public class OrderStateTimeEntity implements Serializable{

    /** 主键 */
    private String id;

    /** 订单状态 */
    private OrderStateEnum orderStateEnum;

    /** 时间 */
    private Timestamp time;



}
