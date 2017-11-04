package com.gaoxi.product.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.product.ProductService;
import com.gaoxi.product.dao.ProductDAO;
import com.gaoxi.rsp.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午8:43
 * @description
 */
@Service(version = "1.0.0")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Result createProduct(ProductEntity productEntity){
        //参数校验
        checkProductParam(productEntity);
        int result = productDAO.createProduct(productEntity);
        if (result==0){
            throw new CommonBizException(ExpCodeEnum.UNKNOW_ERROR);
        }
        return Result.newSuccessResult();
    }

    private void checkProductParam(ProductEntity productEntity){
        if (StringUtils.isEmpty(productEntity.getProdName())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_NAME_NULL);
        }else if (StringUtils.isEmpty(productEntity.getMarketPrice())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_MARKETPRICE_NULL);
        }else if (StringUtils.isEmpty(productEntity.getShopPrice())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_SHOPPRICE_NULL);
        }else if (productEntity.getStock()==0){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_STOCK_ZERO);
        }else if (StringUtils.isEmpty(productEntity.getWeight())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_WEIGHT_NULL);
        }else if (StringUtils.isEmpty(productEntity.getMarketPrice())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_MARKETPRICE_NULL);
        }else if (StringUtils.isEmpty(productEntity.getMarketPrice())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_MARKETPRICE_NULL);
        }
    }
}
