package com.gaoxi.product.service;

import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.facade.product.ProductService;
import com.gaoxi.req.product.*;
import com.gaoxi.rsp.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/11/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;


    @Test
    public void testCreateProduct() throws Exception {
        ProdInsertReq prodInsertReq = new ProdInsertReq();
        prodInsertReq.setProdName("香辣蟹");
        prodInsertReq.setMarketPrice("99.00");
        prodInsertReq.setShopPrice("88.00");
        prodInsertReq.setWeight("100");
        prodInsertReq.setStock(100);
        prodInsertReq.setTopCateEntityID("12345");
        prodInsertReq.setSubCategEntityID("23456");
        //prodInsertReq.setBrandEntityID("lihang");
        //prodInsertReq.setContent("太阳能光伏板");
        prodInsertReq.setCompanyEntityID("xzchain");


        productService.createProduct(prodInsertReq);
    }
    @Test
    public void testUpdateProduct() throws Exception {
        ProdUpdateReq prodUpdateReq = new ProdUpdateReq();
        prodUpdateReq.setId("c142339174e74536bcbe3d277e22e031");
        //prodInsertReq.setProdName("香辣蟹");
        //prodInsertReq.setMarketPrice("99.00");
        //prodInsertReq.setShopPrice("88.00");
//        prodInsertReq.setWeight("100");
//        prodInsertReq.setStock(100);
//        prodInsertReq.setTopCateEntityID("12345");
//        prodInsertReq.setSubCategEntityID("23456");
        //prodInsertReq.setBrandEntityID("lihang");
        prodUpdateReq.setContent("不好吃不要钱");
        //prodInsertReq.setCompanyEntityID("xzchain");


        productService.updateProduct(prodUpdateReq);
    }

    @Test
    public void testCreateCate(){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategory("学弟出品2");
        categoryEntity.setParentId("d87b243d99e341d2b4af8e72bb49f6ad");
        categoryEntity.setSort(11);

        productService.createCategoty(categoryEntity);
    }

    @Test
    public void testUpdateCate(){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId("d87b243d99e341d2b4af8e72bb49f6ad");
        categoryEntity.setSort(35);

        productService.modifyCategory(categoryEntity);
    }

    @Test
    public void testDeleteCate(){
        productService.deleteCategory("3fdebb1b7f144c378b9faf9b4e89a390");
    }

    @Test
    public void testCreateBrand(){
        BrandInsertReq brandInsertReq = new BrandInsertReq();
        brandInsertReq.setBrand("学长");
        brandInsertReq.setBrandLogoUrl("XXXX.abc");
        brandInsertReq.setCompanyEntityId("USER680519fdf9e04d38aef2ceeb10ebb561");

        productService.createBrand(brandInsertReq);
    }

    @Test
    public void testUpdateBrand(){
        BrandInsertReq brandInsertReq = new BrandInsertReq();
        brandInsertReq.setId("8f33629890684f8fbc005edadc2c296c");
        //brandInsertReq.setBrand("学长");
        brandInsertReq.setBrandLogoUrl("lihang.abc");

        productService.modifyBrand(brandInsertReq);
    }

    @Test
    public void testFindBrands(){
        BrandQueryReq brandQueryReq = new BrandQueryReq();
        //brandQueryReq.setId("8f33629890684f8fbc005edadc2c296c");
        brandQueryReq.setBrand("学长");
        //brandQueryReq.setCompanyEntityId("USER680519fdf9e04d38aef2ceeb10ebb561");

        productService.findBrands(brandQueryReq);
        System.out.println();
    }

    @Test
    public void testFindCategorys(){
        CategoryQueryReq categoryQueryReq = new CategoryQueryReq();
        //categoryQueryReq.setId("d87b243d99e341d2b4af8e72bb49f6ad");
        //categoryQueryReq.setCategoryName("学长出品");
        categoryQueryReq.setParentId("d87b243d99e341d2b4af8e72bb49f6ad");

        productService.findCategorys(categoryQueryReq);
    }

    @Test
    public void testFindProducts(){
        ProdQueryReq prodQueryReq = new ProdQueryReq();
//        prodQueryReq.setId("32aa5443ed1340d0843d2dfc153ac066");
//        prodQueryReq.setBrandId("8f33629890684f8fbc005edadc2c296c");
//        prodQueryReq.setCompanyId("USER680519fdf9e04d38aef2ceeb10ebb561");
//        prodQueryReq.setProdName("光伏板");
 //       prodQueryReq.setProdStateCode(1);
//        prodQueryReq.setShopPriceEnd("11");
//        prodQueryReq.setShopPriceStart("99");
        //prodQueryReq.setOrderByPrice(1);
        prodQueryReq.setOrderBySales(1);
//        prodQueryReq.setTopCategoryId("d87b243d99e341d2b4af8e72bb49f6ad");
//        prodQueryReq.setSubCategoryId("79f7f386bee4451dbdcd9df5305d24ac");

        Result<List<ProductEntity>> productEntityList = productService.findProducts(prodQueryReq);
        System.out.println(productEntityList);
    }
}