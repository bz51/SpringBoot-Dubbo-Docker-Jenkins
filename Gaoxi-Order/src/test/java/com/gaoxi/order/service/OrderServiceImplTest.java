package com.gaoxi.order.service;

import com.gaoxi.entity.order.OrdersEntity;
import com.gaoxi.enumeration.order.PayModeEnum;
import com.gaoxi.facade.order.OrderService;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderQueryReq;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private static final String BUYER_ID = "1";
    private static final String SELLER_ID = "2";
    private static final String ORDER_ID = "e9c8108c7d5a4e498bcf2f01699164a8";

    @Test
    public void findOrdersForBuyer() throws Exception {
        OrderQueryReq orderQueryReq = new OrderQueryReq();

        List<OrdersEntity> ordersEntityList = orderService.findOrdersForBuyer(orderQueryReq, BUYER_ID);
        System.out.println(ordersEntityList);
    }

    @Test
    public void findOrdersForSeller() throws Exception {
        OrderQueryReq orderQueryReq = new OrderQueryReq();

        List<OrdersEntity> ordersEntityList = orderService.findOrdersForSeller(orderQueryReq, SELLER_ID);
        System.out.println(ordersEntityList);
    }

    @Test
    public void placeOrder() throws Exception {
        OrderInsertReq orderInsertReq = new OrderInsertReq();
        orderInsertReq.setPayModeCode(PayModeEnum.ALIPAY.getCode());
        orderInsertReq.setLocationId("1");

        Map<String, Integer> prodIdCountMap = Maps.newHashMap();
        prodIdCountMap.put("1",1);
        prodIdCountMap.put("2",10);
        prodIdCountMap.put("3",20);

        orderInsertReq.setProdIdCountMap(prodIdCountMap);

        System.out.println(orderService.placeOrder(orderInsertReq, BUYER_ID));
    }

    @Test
    public void pay() throws Exception {
        orderService.pay(ORDER_ID, BUYER_ID);
    }

    @Test
    public void cancelOrder() throws Exception {
        orderService.cancelOrder(ORDER_ID, BUYER_ID);
    }

    @Test
    public void confirmDelivery() throws Exception {
        orderService.confirmDelivery(ORDER_ID, "11222333", SELLER_ID);
    }

    @Test
    public void confirmReceive() throws Exception {
        orderService.confirmReceive(ORDER_ID, BUYER_ID);
    }

}