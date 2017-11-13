package com.gaoxi.order.component.checkparam;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.order.PayModeEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.product.ProductService;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderProcessReq;
import com.gaoxi.req.product.ProdQueryReq;
import com.gaoxi.utils.EnumUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/7 下午2:23
 * @description 下单参数校验
 */
@Component
public class PlaceOrderCheckParamComponent extends BaseCheckParamComponent {

    @Reference(version = "1.0.0")
    private ProductService productService;

    @Override
    protected void privateParamCheck(OrderProcessContext orderProcessContext) {
        // 获取订单插入请求
        OrderInsertReq orderInsertReq = (OrderInsertReq) orderProcessContext.getOrderProcessReq().getReqData();

        // 买家ID不能为空
        checkParam(orderInsertReq.getUserId(), ExpCodeEnum.USERID_NULL);

        // 支付方式不能为空
        checkParam(orderInsertReq.getPayModeCode(), ExpCodeEnum.PAYMODE_NULL);

        // 支付方式code必须符合枚举
        checkParam(EnumUtil.codeOf(PayModeEnum.class, orderInsertReq.getPayModeCode()), ExpCodeEnum.PAYMODECODE_ERROR);

        // 收货地址不能为空
        checkParam(orderInsertReq.getLocationId(), ExpCodeEnum.LOCATION_NULL);

        // 购买产品的不能为空
        if (orderInsertReq.getProdIdCountMap() == null ||
                orderInsertReq.getProdIdCountMap().size() <= 0) {
            throw new CommonBizException(ExpCodeEnum.PRODUCTIDCOUNT_NULL);
        }

        // 每件产品的数量都必须大于0
        checkProductCount(orderInsertReq.getProdIdCountMap().values());

        // 产品ID列表对应的卖家必须是同一个
        // TODO ProductService本地无法调用，先注释掉!!!
        checkIsSameSeller(orderInsertReq.getProdIdCountMap());
    }

    /**
     * 每件产品的数量都必须大于0
     * @param counts 所有产品的数量
     */
    private void checkProductCount(Collection<Integer> counts) {
        for (Integer count : counts) {
            if (count <= 0) {
                throw new CommonBizException(ExpCodeEnum.PRODUCTIDCOUNT_ERROR);
            }
        }
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
        Set<String> companyIdSet = query(prodQueryReqList);

        // 校验
        if (companyIdSet.size() > 1) {
            throw new CommonBizException(ExpCodeEnum.SELLER_DIFFERENT);
        }
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
     * 根据产品ID查询产品列表
     * @param prodQueryReqList 产品查询请求
     * @return 产品所属公司的ID
     */
    private Set<String> query(List<ProdQueryReq> prodQueryReqList) {
        Set<String> companyIdSet = Sets.newHashSet();

        for (ProdQueryReq prodQueryReq : prodQueryReqList) {
            ProductEntity productEntity = productService.findProducts(prodQueryReq).getData().get(0);
            companyIdSet.add(productEntity.getCompanyEntity().getId());
        }

        return companyIdSet;
    }
}
