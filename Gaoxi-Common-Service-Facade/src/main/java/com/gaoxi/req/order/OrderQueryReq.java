package com.gaoxi.req.order;

import com.gaoxi.req.QueryReq;


/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午3:21
 *
 * @description 订单查询请求
 */
public class OrderQueryReq extends QueryReq {

    /** 订单编号(主键) */
    private String id;

    /** 买家ID(买家只能查自己的订单) */
    private String buyerId;

    /** 买家用户名(模糊查询) */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家邮箱 */
    private String buyerMail;

    /** 卖家ID(卖家只能查自己的订单) */
    private String sellerId;

    /** 卖家的企业名称(模糊查询) */
    private String sellerCompanyName;

    /** 卖家手机 */
    private String sellerPhone;

    /** 卖家邮箱 */
    private String sellerMail;

    /** 订单状态 {@link com.gaoxi.enumeration.order.OrderStateEnum} */
    private Integer orderStateCode;

    /** 下单起始时间 */
    private String placeOrderStartTime;
    /** 下单结束时间 */
    private String placeOrderEndTime;

    /** 收件人姓名(模糊匹配) */
    private String recipientName;

    /** 收件人手机 */
    private String recipientPhone;

    /** 收货地址(模糊匹配) */
    private String recipientLocation;

    /** 支付方式 {@link com.gaoxi.enumeration.order.PayModeEnum} */
    private Integer payModeCode;

    /** 物流单号 */
    private String expressNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerMail() {
        return buyerMail;
    }

    public void setBuyerMail(String buyerMail) {
        this.buyerMail = buyerMail;
    }

    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerMail() {
        return sellerMail;
    }

    public void setSellerMail(String sellerMail) {
        this.sellerMail = sellerMail;
    }

    public Integer getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(Integer orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getPlaceOrderStartTime() {
        return placeOrderStartTime;
    }

    public void setPlaceOrderStartTime(String placeOrderStartTime) {
        this.placeOrderStartTime = placeOrderStartTime;
    }

    public String getPlaceOrderEndTime() {
        return placeOrderEndTime;
    }

    public void setPlaceOrderEndTime(String placeOrderEndTime) {
        this.placeOrderEndTime = placeOrderEndTime;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientLocation() {
        return recipientLocation;
    }

    public void setRecipientLocation(String recipientLocation) {
        this.recipientLocation = recipientLocation;
    }

    public Integer getPayModeCode() {
        return payModeCode;
    }

    public void setPayModeCode(Integer payModeCode) {
        this.payModeCode = payModeCode;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "OrderQueryReq{" +
                "id='" + id + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerPhone='" + buyerPhone + '\'' +
                ", buyerMail='" + buyerMail + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerCompanyName='" + sellerCompanyName + '\'' +
                ", sellerPhone='" + sellerPhone + '\'' +
                ", sellerMail='" + sellerMail + '\'' +
                ", orderStateCode=" + orderStateCode +
                ", placeOrderStartTime='" + placeOrderStartTime + '\'' +
                ", placeOrderEndTime='" + placeOrderEndTime + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", recipientLocation='" + recipientLocation + '\'' +
                ", payModeCode=" + payModeCode +
                ", expressNo='" + expressNo + '\'' +
                ", page=" + page +
                ", numPerPage=" + numPerPage +
                ", currentPage=" + currentPage +
                '}';
    }
}
