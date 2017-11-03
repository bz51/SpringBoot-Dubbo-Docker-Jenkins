package com.gaoxi.controller.product;

import com.gaoxi.annotation.Login;
import com.gaoxi.annotation.Permission;
import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.req.product.ProdQueryReq;
import com.gaoxi.rsp.Result;
import com.gaoxi.entity.product.ImageEntity;
import com.gaoxi.entity.product.ProductEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @PostMapping("product")
//    @Login
//    @Permission("product:create")
    public Result createProduct(ProductEntity productEntity);

    /**
     * 上传图片
     * PS：需要定时清除没被引用的图片
     * @param file 待上传的文件
     * @return 图片详情
     */
    @PostMapping("image")
    @Login
//    @Permission("image:upload")
    public Result<ImageEntity> uploadImage(MultipartFile file);

    /**
     * 修改产品
     * @param productEntity 待修改产品（id必填 & 只提交待修改字段即可）
     * @return 是否修改成功
     */
    @PutMapping("product")
    @Login
    @Permission("product:update")
    public Result updateProduct(ProductEntity productEntity);

    /**
     * 查询产品
     * @param prodQueryReq 产品查询请求
     * @return 产品查询结果
     */
    @GetMapping("product")
    @Login
    @Permission("product:query")
    public Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq);

    /**
     * 创建产品类别
     * @param categoryEntity 产品类别参数
     * @return 是否创建成功
     */
    @PostMapping("category")
    @Login
    @Permission("category:create")
    public Result createCategoty(CategoryEntity categoryEntity);

    /**
     * 修改产品类别
     * PS：只能修改：类别名称、排序，且id必填
     * @param categoryEntity 待修改类别
     * @return 是否修改成
     */
    @PutMapping("category")
    @Login
    @Permission("category:update")
    public Result modifyCategory(CategoryEntity categoryEntity);

    /**
     * 删除类别
     * PS：只有当该类别下没有产品时才允许删除
     * @param categoryId 待删除类别的id
     * @return 删除结果
     */
    @DeleteMapping("category/{categoryId}")
    @Login
    @Permission("category:delete")
    public Result deleteCategory(String categoryId);

    /**
     * 创建品牌
     * @param brandEntity 品牌参数(其中品牌所属企业字段仅需填写企业id即可)
     * @return 是否创建成功
     */
    @PostMapping("brand")
    @Login
    @Permission("brand:create")
    public Result createBrand(BrandEntity brandEntity);

    /**
     * 修改品牌
     * @param brandEntity 待修改品牌(品牌id必填)
     * @return 是否修改成功
     */
    @PutMapping("brand")
    @Login
    @Permission("brand:update")
    public Result modifyBrand(BrandEntity brandEntity);

    /**
     * 删除品牌
     * PS：只有当该品牌下没有产品时才允许删除
     * @param brandId 待删除品牌的id
     * @return 是否删除成功
     */
    @DeleteMapping("brand/{brandId}")
    @Login
    @Permission("brand:delete")
    public Result deleteBrand(String brandId);
}
