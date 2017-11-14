package com.gaoxi.order.component.createorder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.order.OrderStateTimeEntity;
import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.entity.order.ProductOrderEntity;
import com.gaoxi.entity.order.ReceiptEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.entity.user.LocationEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.order.OrderStateEnum;
import com.gaoxi.enumeration.order.PayModeEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.product.ProductService;
import com.gaoxi.order.component.datatransfer.ProdCountMapTransferComponent;
import com.gaoxi.order.dao.OrderDAO;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.product.ProdQueryReq;
import com.gaoxi.utils.EnumUtil;
import com.gaoxi.utils.KeyGenerator;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
@Component
public class CreateOrderComponent extends BaseComponent {

    @Autowired
    private OrderDAO orderDAO;

    @Reference(version = "1.0.0")
    private ProductService productService;

    @Override
    public void handle(OrderProcessContext orderProcessContext) {

        preHandle(orderProcessContext);

        // 获取订单插入请求
        OrderInsertReq orderInsertReq = (OrderInsertReq) orderProcessContext.getOrderProcessReq().getReqData();

        // 创建订单
        createOrder(orderInsertReq);

        afterHandle(orderProcessContext);

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
        // 计算总价
        String orderTotalPrice = calculateTotalPrice(orderInsertReq);

        // 插入order表
        String orderId = insertOrder(orderInsertReq, orderTotalPrice);

        // 插入product_order表
        insertOrderProduct(orderInsertReq.getProdIdCountMap(), orderId);

        // 插入order_state_time表
        insertOrderStateTime(orderId);
    }

    /**
     * 插入order_state_time表
     * @param orderId
     */
    private void insertOrderStateTime(String orderId) {
        OrderStateTimeEntity orderStateTimeEntity = new OrderStateTimeEntity();
        orderStateTimeEntity.setOrderId(orderId);
        orderStateTimeEntity.setTime(new Timestamp(System.currentTimeMillis()));
        orderStateTimeEntity.setOrderStateEnum(OrderStateEnum.INIT);

        orderDAO.insertOrderStateTime(orderStateTimeEntity);
    }

    /**
     * 插入product_order表
     * @param prodIdCountMap
     * @param orderId 订单编号
     */
    private void insertOrderProduct(Map<String, Integer> prodIdCountMap, String orderId) {
        List<ProductOrderEntity> productOrderEntityList = Lists.newArrayList();

        for (String prodId : prodIdCountMap.keySet()) {
            ProductOrderEntity productOrderEntity = new ProductOrderEntity();

            // 数量
            productOrderEntity.setCount(prodIdCountMap.get(prodId));

            // 产品ID
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(prodId);
            productOrderEntity.setProductEntity(productEntity);

            // 订单Id
            productOrderEntity.setOrderId(orderId);

            productOrderEntityList.add(productOrderEntity);
        }

        // 插入
        for (ProductOrderEntity productOrderEntity : productOrderEntityList) {
            orderDAO.insertOrderProduct(productOrderEntity);
        }
    }

    /**
     * 插入Order表
     * @param orderInsertReq 订单插入请求
     * @param orderTotalPrice 订单总价
     * @return orderId
     */
    private String insertOrder(OrderInsertReq orderInsertReq, String orderTotalPrice) {
        // 构造OrdersEntity
        OrdersEntity ordersEntity = buildOrdersEntity(orderInsertReq, orderTotalPrice);

        // 插入
        orderDAO.insertOrder(ordersEntity);

        return ordersEntity.getId();
    }

    /**
     * 构造OrdersEntity
     * @param orderInsertReq
     * @param orderTotalPrice
     * @return
     */
    private OrdersEntity buildOrdersEntity(OrderInsertReq orderInsertReq, String orderTotalPrice) {
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setId(KeyGenerator.getKey());

        UserEntity buyer = new UserEntity();
        buyer.setId(orderInsertReq.getUserId());
        ordersEntity.setBuyer(buyer);

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(orderInsertReq.getLocationId());
        ordersEntity.setLocationEntity(locationEntity);

        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setId(orderInsertReq.getReceiptId());
        ordersEntity.setReceiptEntity(receiptEntity);

        ordersEntity.setPayModeEnum(EnumUtil.codeOf(PayModeEnum.class, orderInsertReq.getPayModeCode()));

        ordersEntity.setRemark(orderInsertReq.getRemark());

        ordersEntity.setCompany(orderInsertReq.getProdEntityCountMap().keySet().toArray(new ProductEntity[1])[0].getCompanyEntity());

        ordersEntity.setTotalPrice(orderTotalPrice);

        ordersEntity.setOrderStateEnum(OrderStateEnum.INIT);

        return ordersEntity;
    }

    /**
     * 计算订单总价
     * @param orderInsertReq 订单插入请求
     * @return 订单总价
     */
    private String calculateTotalPrice(OrderInsertReq orderInsertReq) {
        // 获取prodEntityCountMap
        Map<ProductEntity, Integer> prodEntityCountMap = orderInsertReq.getProdEntityCountMap();

        // 计算订单总价
        BigDecimal orderTotalPrice = new BigDecimal("0");
        for (ProductEntity productEntity : prodEntityCountMap.keySet()) {
            // 本店单价
            BigDecimal shopPrice = new BigDecimal(productEntity.getShopPrice());
            // 购买数量
            BigDecimal count = new BigDecimal(prodEntityCountMap.get(productEntity));
            // 单品总价(本店单价*购买数量)
            BigDecimal prodTotalPrice = shopPrice.multiply(count);
            // 订单总价
            orderTotalPrice = orderTotalPrice.add(prodTotalPrice);
        }

        // 保留两位小数 & 四舍五入
        return orderTotalPrice.setScale(2).toString();
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

}
