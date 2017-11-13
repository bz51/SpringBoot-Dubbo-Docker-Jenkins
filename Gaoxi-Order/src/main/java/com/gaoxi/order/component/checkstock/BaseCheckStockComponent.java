package com.gaoxi.order.component.checkstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.product.ProductService;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderProcessReq;
import com.gaoxi.req.product.ProdQueryReq;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/7 下午1:17
 * @description 库存校验组件
 */
public class BaseCheckStockComponent extends BaseComponent {

    @Reference
    private ProductService productService;

    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);

        // 获取产品Map
        OrderInsertReq orderInsertReq = (OrderInsertReq) orderProcessContext.getOrderProcessReq().getReqData();
        Map<String ,Integer> prodIdCountMap = orderInsertReq.getProdIdCountMap();

        // 检查库存
        checkStock(prodIdCountMap);

        afterHandle(orderProcessContext);
    }

    /**
     * 校验库存是否足够
     * @param prodIdCountMap 产品-购买数量 集合
     */
    private void checkStock(Map<String, Integer> prodIdCountMap) {
        // 查询产品库存
        List<ProductEntity> productEntityList = queryProduct(prodIdCountMap);

        // 校验库存
        for (ProductEntity productEntity : productEntityList) {
            // 获取购买量
            Integer count = prodIdCountMap.get(productEntity.getId());
            // 校验
            if (productEntity.getStock() < count) {
                throw new CommonBizException(ExpCodeEnum.STOCK_LOW);
            }
        }
    }

    /**
     * 查询产品详情
     * @param prodIdCountMap 产品ID-库存 映射
     * @return 产品列表
     */
    private List<ProductEntity> queryProduct(Map<String, Integer> prodIdCountMap) {
        // 查询结果集
        List<ProductEntity> productEntityList = Lists.newArrayList();

        // 构建查询请求
        List<ProdQueryReq> prodQueryReqList = buildProdQueryReq(prodIdCountMap);

        // 批量查询
        for (ProdQueryReq prodQueryReq : prodQueryReqList) {
            List<ProductEntity> productEntitys = productService.findProducts(prodQueryReq).getData();

            // 产品ID不存在
            if (productEntitys.size() <= 0) {
                throw new CommonBizException(ExpCodeEnum.PRODUCT_ID_NO_EXISTENT);
            }

            productEntityList.add(productEntitys.get(0));
        }
        return productEntityList;
    }

    /**
     * 构建产品查询请求
     * @param prodIdCountMap 产品ID-库存 映射
     * @return 产品查询请求列表
     */
    private List<ProdQueryReq> buildProdQueryReq(Map<String, Integer> prodIdCountMap) {
        List<ProdQueryReq> prodQueryReqList = Lists.newArrayList();

        for (String prodId : prodIdCountMap.keySet()) {
            ProdQueryReq prodQueryReq = new ProdQueryReq();
            prodQueryReq.setId(prodId);
            prodQueryReqList.add(prodQueryReq);
        }

        return prodQueryReqList;
    }
}
