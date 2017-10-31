package com.gaoxi.controller.product;

import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.req.product.ProdQueryReq;
import com.gaoxi.rsp.Result;
import com.gaoxi.entity.product.ImageEntity;
import com.gaoxi.entity.product.ProductEntity;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/27 下午10:28
 * @description 产品系统的Controller
 */
public interface ProductController {

    /**
     * 创建产品
     * @param productEntity 产品详情
     * @return 是否创建成功
     */
    public Result createProduct(ProductEntity productEntity);

    /**
     * 上传图片
     * PS：需要定时清除没被引用的图片
     * @param file 待上传的文件
     * @return 图片详情
     */
    public Result<ImageEntity> uploadImage(MultipartFile file);

    /**
     * 修改产品
     * @param productEntity 待修改产品（id必填 & 只提交待修改字段即可）
     * @return 是否修改成功
     */
    public Result updateProduct(ProductEntity productEntity);

    /**
     * 查询产品
     * @param prodQueryReq 产品查询请求
     * @return 产品查询结果
     */
    public Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq);

    /**
     * 创建产品类别
     * @param categoryEntity 产品类别参数
     * @return 是否创建成功
     */
    public Result createCategoty(CategoryEntity categoryEntity);

    /**
     * 修改产品类别
     * PS：只能修改：类别名称、排序，且id必填
     * @param categoryEntity 待修改类别
     * @return 是否修改成
     */
    public Result modifyCategory(CategoryEntity categoryEntity);

    /**
     * 删除类别
     * PS：只有当该类别下没有产品时才允许删除
     * @param categoryId 待删除类别的id
     * @return 删除结果
     */
    public Result deleteCategory(String categoryId);

    /**
     * 创建品牌
     * @param brandEntity 品牌参数(其中品牌所属企业字段仅需填写企业id即可)
     * @return 是否创建成功
     */
    public Result createBrand(BrandEntity brandEntity);

    /**
     * 修改品牌
     * @param brandEntity 待修改品牌(品牌id必填)
     * @return 是否修改成功
     */
    public Result modifyBrand(BrandEntity brandEntity);

    /**
     * 删除品牌
     * PS：只有当该品牌下没有产品时才允许删除
     * @param brandId 待删除品牌的id
     * @return 是否删除成功
     */
    public Result deleteBrand(String brandId);
}
