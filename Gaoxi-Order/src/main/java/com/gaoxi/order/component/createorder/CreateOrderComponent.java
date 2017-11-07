package com.gaoxi.order.component.createorder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.order.PayModeEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.product.ProductService;
import com.gaoxi.order.dao.OrderDAO;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderProcessReq;
import com.gaoxi.req.product.ProdQueryReq;
import com.gaoxi.utils.EnumUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午3:56
 *
 * @description 创建订单组件
 */
public class CreateOrderComponent extends BaseComponent {

    @Autowired
    private OrderDAO orderDAO;

    @Reference
    private ProductService productService;

    @Override
    public void handle(OrderProcessReq orderProcessReq) {

        // 获取订单创建请求
        OrderInsertReq orderInsertReq = getOrderInsertReq(orderProcessReq);

        // 参数校验
        checkParam(orderInsertReq);

        // 构造OrdersEntity
        OrdersEntity ordersEntity = buildUserEntity(orderInsertReq);

        // 创建订单
        createOrder(orderInsertReq);


    }

    /**
     * 构造UserEntity
     * @param orderInsertReq 订单创建请求
     * @return OrdersEntity
     */
    private OrdersEntity buildUserEntity(OrderInsertReq orderInsertReq) {
        return null;
    }

    /**
     * 创建订单
     * @param orderInsertReq 订单创建请求
     */
    private void createOrder(OrderInsertReq orderInsertReq) {

    }

    /**
     * 参数校验
     * @param orderInsertReq 订单创建请求
     */
    private void checkParam(OrderInsertReq orderInsertReq) {
        // 买家ID不能为空
        if (StringUtils.isEmpty(orderInsertReq.getUserId())) {
            throw new CommonBizException(ExpCodeEnum.USERID_NULL);
        }

        // 支付方式不能为空 & 符合枚举值
        if (orderInsertReq.getPayModeCode() == null ||
                EnumUtil.codeOf(PayModeEnum.class, orderInsertReq.getPayModeCode()) == null) {
            throw new CommonBizException(ExpCodeEnum.PAYMODE_NULL);
        }

        // 收货地址不能为空
        if (StringUtils.isEmpty(orderInsertReq.getLocationId())) {
            throw new CommonBizException(ExpCodeEnum.LOCATION_NULL);
        }

        // 所需购买的产品ID和对应的数量不能为空
        if (orderInsertReq.getProdIdCountMap() == null ||
                orderInsertReq.getProdIdCountMap().size() <= 0) {
            throw new CommonBizException(ExpCodeEnum.PRODUCTIDCOUNT_NULL);
        }

        // 检查产品ID列表对应的卖家是否是同一个
        checkIsSameSeller(orderInsertReq.getProdIdCountMap());
    }

    /**
     * 检查产品ID列表对应的卖家是否是同一个
     * @param prodIdCountMap 产品ID-数量 的map
     */
    private void checkIsSameSeller(Map<String, Integer> prodIdCountMap) {
        // 获取prodcutID集合
        Set<String> productIdSet = prodIdCountMap.keySet();

        // 构造查询请求
        List<ProdQueryReq> prodQueryReqList = buildOrderQueryReq(productIdSet);

        // 查询
        List<ProductEntity> productEntityList = query(prodQueryReqList);

        // 校验 TODO lamada表达式还要检查
        Map<UserEntity, List<ProductEntity>> companyMap = productEntityList.stream().collect(groupingBy(ProductEntity::getCompanyEntity));
        if (companyMap.size() > 1) {
            throw new CommonBizException(ExpCodeEnum.SELLER_DIFFERENT);
        }

    }

    /**
     * 根据产品ID查询产品列表
     * @param prodQueryReqList 产品查询请求
     * @return 产品列表
     */
    private List<ProductEntity> query(List<ProdQueryReq> prodQueryReqList) {
        List<ProductEntity> productEntityList = Lists.newArrayList();

        for (ProdQueryReq prodQueryReq : prodQueryReqList) {
            ProductEntity productEntity = productService.findProducts(prodQueryReq).getData().get(0);
            productEntityList.add(productEntity);
        }

        return productEntityList;
    }

    /**
     * 构造查询请求
     * @param productIdSet 产品ID集合
     * @return 查询请求列表
     */
    private List<ProdQueryReq> buildOrderQueryReq(Set<String> productIdSet) {
        List<ProdQueryReq> prodQueryReqList = Lists.newArrayList();

        for (String productId : productIdSet) {
            ProdQueryReq prodQueryReq = new ProdQueryReq();
            prodQueryReq.setId(productId);
            prodQueryReqList.add(prodQueryReq);
        }

        return prodQueryReqList;
    }


    /**
     * 获取订单创建请求
     * @param orderProcessReq 订单受理请求
     * @return 订单创建请求
     */
    private OrderInsertReq getOrderInsertReq(OrderProcessReq orderProcessReq) {
        Object orderInsertReq = orderProcessReq.getT();
        if (orderInsertReq == null) {
            throw new CommonBizException(ExpCodeEnum.ORDER_INSERT_REQ_NULL);
        }

        return (OrderInsertReq) orderInsertReq;
    }

}
