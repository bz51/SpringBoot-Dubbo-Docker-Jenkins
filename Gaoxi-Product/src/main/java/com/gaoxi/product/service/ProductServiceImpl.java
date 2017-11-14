package com.gaoxi.product.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.enumeration.product.ProdStateEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.product.ProductService;
import com.gaoxi.product.dao.BrandDAO;
import com.gaoxi.product.dao.CategoryDAO;
import com.gaoxi.product.dao.ProductDAO;
import com.gaoxi.req.QueryReq;
import com.gaoxi.req.product.*;
import com.gaoxi.rsp.Result;
import com.gaoxi.utils.KeyGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午8:43
 * @description
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Override
    public Result createProduct(ProdInsertReq prodInsertReq){
        //参数校验
        checkProductParam(prodInsertReq);
        //组装新产品
        ProdInsertReq product = makeProdInsertReq(prodInsertReq);
        //新增产品
        int result = productDAO.createProduct(product);
        if (result == 0){
            //新增失败
            throw new CommonBizException(ExpCodeEnum.PRODUCT_CREATE_FAIL);
        }
        //新增成功
        return Result.newSuccessResult();
    }

    @Override
    public Result updateProduct(ProdUpdateReq prodUpdateReq) {
        //增量更新产品
        int result = productDAO.updateProduct(prodUpdateReq);
        if (result == 0){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_UPDATE_FAIL);
        }
        return Result.newSuccessResult();
    }

    @Override
    public Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq) {
        //查询产品详情
        List<ProductEntity> productEntityList= productDAO.findProducts(prodQueryReq);
        if (CollectionUtils.isEmpty(productEntityList)){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_SELECT_FAIL);
        }
        return Result.newSuccessResult(productEntityList);
    }

    @Override
    public Result createCategoty(CategoryEntity categoryEntity) {
        //校验参数，类别名称不能为空
        if (StringUtils.isEmpty(categoryEntity.getCategory())){
            throw new CommonBizException(ExpCodeEnum.CATEGORY_NAME_NULL);
        }
        //组装新增类别
        CategoryEntity category = makeCateInsert(categoryEntity);
        int result = categoryDAO.createCategoty(category);
        if (result == 0){
            throw new CommonBizException(ExpCodeEnum.CATEGORY_CREATE_FAIL);
        }
        return Result.newSuccessResult();
    }

    @Override
    public Result modifyCategory(CategoryEntity categoryEntity) {
        //增量更新类别
        int result = categoryDAO.updateCategory(categoryEntity);
        if (result == 0){
            throw new CommonBizException(ExpCodeEnum.CATEGORY_UPDATE_FAIL);
        }
        return Result.newSuccessResult();
    }

    @Override
    public Result deleteCategory(String categoryId) {
        //判断当前类别是否已经被使用
        int result = productDAO.findUsedCategory(categoryId);
        if (result>0){
            throw new CommonBizException(ExpCodeEnum.CATEGORY_HASUSED);
        }
        //当前类别未被使用，可以删除
        result = categoryDAO.deleteCategory(categoryId);
        if (result == 0){
            throw new CommonBizException(ExpCodeEnum.CATEGORY_DELETE_FAIL);
        }
        return Result.newSuccessResult();
    }

    @Override
    public Result createBrand(BrandInsertReq brandInsertReq) {
        //校验参数
        checkBrandParam(brandInsertReq);
        //组装新增品牌
        BrandInsertReq brand = makeBrandInsert(brandInsertReq);
        int result = brandDAO.createBrand(brand);
        if (result == 0){
            throw new CommonBizException(ExpCodeEnum.BRADN_CREATE_FAIL);
        }
        return Result.newSuccessResult();
    }

    @Override
    public Result modifyBrand(BrandInsertReq brandInsertReq) {
        //更新类别
        int result = brandDAO.updateBrand(brandInsertReq);
        if (result == 0){
            throw new CommonBizException(ExpCodeEnum.BRADN_UPDATE_FAIL);
        }
        return Result.newSuccessResult();
    }

    @Override
    public Result<List<CategoryEntity>> findCategorys(CategoryQueryReq categoryQueryReq) {
        //查询类别
        List<CategoryEntity> categoryEntityList = categoryDAO.findCategorys(categoryQueryReq);
        if (CollectionUtils.isEmpty(categoryEntityList)){
            throw new CommonBizException(ExpCodeEnum.CATEGORY_SELECT_FAIL);
        }
        return Result.newSuccessResult(categoryEntityList);
    }

    @Override
    public Result<List<BrandEntity>> findBrands(BrandQueryReq brandQueryReq) {
        //查询品牌
        List<BrandEntity> brandEntityList = brandDAO.findBrands(brandQueryReq);
        if (CollectionUtils.isEmpty(brandEntityList)){
            throw new CommonBizException(ExpCodeEnum.BRADN_SELETE_FAIL);
        }
        return Result.newSuccessResult(brandEntityList);
    }

    /**
     * 校验新增产品时参数
     * 关键字段不能为空
     * @param prodInsertReq
     */
    private void checkProductParam(ProdInsertReq prodInsertReq){
        if (StringUtils.isEmpty(prodInsertReq.getProdName())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_NAME_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getMarketPrice())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_MARKETPRICE_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getShopPrice())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_SHOPPRICE_NULL);
        }else if (prodInsertReq.getStock()==0){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_STOCK_ZERO);
        }else if (StringUtils.isEmpty(prodInsertReq.getWeight())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_WEIGHT_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getTopCateEntityID())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_TOPCATEENTITY_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getSubCategEntityID())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_SUBCATEGENTITY_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getBrandEntityID())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_BRANDENTITY_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getCompanyEntityID())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_COMPANYENTITY_NULL);
        }
    }

    /**
     * 校验新增品牌时参数
     * 关键字段不能为空
     * @param brandInsertReq
     */
    public void checkBrandParam(BrandInsertReq brandInsertReq){
        if (StringUtils.isEmpty(brandInsertReq.getBrand())){
            throw new CommonBizException(ExpCodeEnum.BRADN_NAME_NULL);
        }else if (StringUtils.isEmpty(brandInsertReq.getBrandLogoUrl())){
            throw new CommonBizException(ExpCodeEnum.BRADN_LOGO_NULL);
        }else if (StringUtils.isEmpty(brandInsertReq.getCompanyEntityId())){
            throw new CommonBizException(ExpCodeEnum.BRADN_COMMPANY_NULL);
        }
    }
    /**
     * 组装新增产品对象
     * @param prodInsertReq
     * @return
     */
    private ProdInsertReq makeProdInsertReq(ProdInsertReq prodInsertReq){
        ProdInsertReq newProduct = new ProdInsertReq();
        //使用BeanUtils复制属性，注意顺序！
        BeanUtils.copyProperties(prodInsertReq,newProduct);
        newProduct.setId(KeyGenerator.getKey());
        //新增产品默认销量为0
        newProduct.setSales(0);
        //新增产品默认状态是上架
        newProduct.setProdState(ProdStateEnum.OPEN.getCode());
        return newProduct;
    }

    /**
     * 组装新增类别对象
     * @param categoryEntity
     * @return
     */
    private CategoryEntity makeCateInsert(CategoryEntity categoryEntity){
        CategoryEntity newCategory = new CategoryEntity();
        BeanUtils.copyProperties(categoryEntity,newCategory);
        newCategory.setId(KeyGenerator.getKey());
        return newCategory;
    }

    /**
     * 组装新增品牌对象
     * @param brandInsertReq
     * @return
     */
    private BrandInsertReq makeBrandInsert(BrandInsertReq brandInsertReq){
        BrandInsertReq newBrand = new BrandInsertReq();
        BeanUtils.copyProperties(brandInsertReq,newBrand);
        newBrand.setId(KeyGenerator.getKey());
        return newBrand;
    }

}
