package com.gaoxi.entity.order;

import com.gaoxi.enumeration.order.ReceiptTypeEnum;

import java.io.Serializable;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 上午11:04
 *
 * @description 发票信息实体类
 */
public class ReceiptEntity implements Serializable {

    /** 主键 */
    private String id;

    /** 发票类型 */
    private ReceiptTypeEnum receiptTypeEnum;

    /** 发票抬头 */
    private String title;

    /** 纳税人识别号 */
    private String taxpayerNo;

    /** 发票内容 */
    private String content;

}
