package com.gaoxi.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
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
    @PostMapping("product")
    public Result createProduct(ProductEntity productEntity) {
        return null;
    }

    @Override
    @PostMapping("image")
    public Result<ImageEntity> uploadImage(MultipartFile file) {
        return null;
    }

    @Override
    @PutMapping("product")
    public Result updateProduct(ProductEntity productEntity) {
        return null;
    }

    @Override
    @GetMapping("product")
    public Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq) {
        return null;
    }

    @Override
    @PostMapping("category")
    public Result createCategoty(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    @PutMapping("category")
    public Result modifyCategory(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    @DeleteMapping("category/{categoryId}")
    public Result deleteCategory(String categoryId) {
        return null;
    }

    @Override
    @PostMapping("brand")
    public Result createBrand(BrandEntity brandEntity) {
        return null;
    }

    @Override
    @PutMapping("brand")
    public Result modifyBrand(BrandEntity brandEntity) {
        return null;
    }

    @Override
    @DeleteMapping("brand/{brandId}")
    public Result deleteBrand(String brandId) {
        return null;
    }
}
