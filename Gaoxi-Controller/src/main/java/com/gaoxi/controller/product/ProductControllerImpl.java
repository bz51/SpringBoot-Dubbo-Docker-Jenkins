package com.gaoxi.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.annotation.Login;
import com.gaoxi.annotation.Role;
import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.entity.product.ImageEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.facade.product.ProductService;
import com.gaoxi.req.product.ProdQueryReq;
import com.gaoxi.rsp.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/27 下午10:28
 */
@RestController
public class ProductControllerImpl implements ProductController {

    @Reference(version = "1.0.0")
    private ProductService productService;


    @Override
    public Result createProduct(ProductEntity productEntity) {
        return null;
    }

    @Override
    public Result<ImageEntity> uploadImage(MultipartFile file) {
        return null;
    }

    @Override
    public Result updateProduct(ProductEntity productEntity) {
        return null;
    }

    @Override
    public Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq) {
        return null;
    }

    @Override
    public Result createCategoty(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    public Result modifyCategory(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    public Result deleteCategory(String categoryId) {
        return null;
    }

    @Override
    public Result createBrand(BrandEntity brandEntity) {
        return null;
    }

    @Override
    public Result modifyBrand(BrandEntity brandEntity) {
        return null;
    }

    @Override
    public Result deleteBrand(String brandId) {
        return null;
    }
}
