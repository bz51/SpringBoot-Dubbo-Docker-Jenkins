package com.gaoxi.order.component.datatransfer;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.enumeration.product.ProdStateEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.product.ProductService;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.product.ProdQueryReq;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/13 下午2:35
 *
 * @description 产品ID-数量的Map——>产品Entity-数量的Map
 */
@Component
public class ProdCountMapTransferComponent extends BaseDataTransferComponent {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "1.0.0")
    private ProductService productService;

    @Override
    protected void transfer(OrderProcessContext orderProcessContext) {
        // 获取ProdIdCountMap
        Map<String, Integer> prodIdCountMap = getProdIdCountMap(orderProcessContext);

        // 查询产品详情
        List<ProductEntity> productEntityList = queryProduct(prodIdCountMap);

        // 构建Map<ProductEntity, Integer>
        Map<ProductEntity, Integer> productEntityIntegerMap = buildProductEntityIntegerMap(productEntityList, prodIdCountMap);

        // 放入上下文
        putIntoContext(orderProcessContext, productEntityIntegerMap);
    }

    /**
     * 放入上下文
     * @param orderProcessContext
     * @param productEntityIntegerMap
     */
    private void putIntoContext(OrderProcessContext orderProcessContext, Map<ProductEntity, Integer> productEntityIntegerMap) {
        OrderInsertReq orderInsertReq = (OrderInsertReq) orderProcessContext.getOrderProcessReq().getReqData();
        orderInsertReq.setProdEntityCountMap(productEntityIntegerMap);
    }

    /**
     * 构建Map<ProductEntity, Integer>
     * @param productEntityList
     * @param prodIdCountMap
     * @return
     */
    private Map<ProductEntity, Integer> buildProductEntityIntegerMap(List<ProductEntity> productEntityList, Map<String, Integer> prodIdCountMap) {
        Map<ProductEntity, Integer> map = Maps.newHashMap();

        if (CollectionUtils.isEmpty(productEntityList)) {
            return map;
        }

        for (ProductEntity productEntity : productEntityList) {
            Integer count = prodIdCountMap.get(productEntity.getId());
            map.put(productEntity, count);
        }
        return map;
    }

    /**
     * 获取Context中的ProdIdCountMap
     * @param orderProcessContext 上下文
     * @return ProdIdCountMap
     */
    private Map<String, Integer> getProdIdCountMap(OrderProcessContext orderProcessContext) {
        OrderInsertReq orderInsertReq = (OrderInsertReq) orderProcessContext.getOrderProcessReq().getReqData();
        return orderInsertReq.getProdIdCountMap();
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
                logger.error("查询产品详情时，上线中 & 产品ID=" + prodQueryReq.getId() + "的产品不存在！");
                throw new CommonBizException(ExpCodeEnum.PRODUCT_NO_EXISTENT);
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
            // 必须是"上线中"的产品
            prodQueryReq.setProdStateCode(ProdStateEnum.OPEN.getCode());
            prodQueryReqList.add(prodQueryReq);
        }

        return prodQueryReqList;
    }
}
