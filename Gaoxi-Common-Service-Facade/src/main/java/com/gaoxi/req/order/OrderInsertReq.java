package com.gaoxi.req.order;

import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.req.AbsReq;

import java.util.Map;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午4:08
 *
 * @description 创建订单的请求
 */
public class OrderInsertReq extends AbsReq {

    /** 买家ID */
    private String userId;

    /** 产品ID-数量 */
    private String prodIdCountJson;
    private Map<String, Integer> prodIdCountMap;
    private Map<ProductEntity, Integer> prodEntityCountMap;

    /** 支付方式 {@link com.gaoxi.enumeration.order.PayModeEnum} */
    private Integer payModeCode;

    /** 发票信息的ID */
    private String receiptId;

    /** 收货地址的ID */
    private String locationId;

    /** 买家备注信息 */
    private String remark;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getProdIdCountMap() {
        return prodIdCountMap;
    }

    public void setProdIdCountMap(Map<String, Integer> prodIdCountMap) {
        this.prodIdCountMap = prodIdCountMap;
    }

    public Integer getPayModeCode() {
        return payModeCode;
    }

    public void setPayModeCode(Integer payModeCode) {
        this.payModeCode = payModeCode;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<ProductEntity, Integer> getProdEntityCountMap() {
        return prodEntityCountMap;
    }

    public void setProdEntityCountMap(Map<ProductEntity, Integer> prodEntityCountMap) {
        this.prodEntityCountMap = prodEntityCountMap;
    }

    public String getProdIdCountJson() {
        return prodIdCountJson;
    }

    public void setProdIdCountJson(String prodIdCountJson) {
        this.prodIdCountJson = prodIdCountJson;
    }

    @Override
    public String toString() {
        return "OrderInsertReq{" +
                "userId='" + userId + '\'' +
                ", prodIdCountJson='" + prodIdCountJson + '\'' +
                ", prodIdCountMap=" + prodIdCountMap +
                ", prodEntityCountMap=" + prodEntityCountMap +
                ", payModeCode=" + payModeCode +
                ", receiptId='" + receiptId + '\'' +
                ", locationId='" + locationId + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
