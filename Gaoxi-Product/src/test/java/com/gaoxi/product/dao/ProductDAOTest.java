package com.gaoxi.product.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/11/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDAOTest {
    @Autowired
    private ProductDAO productDAO;

    @Test
    public void testFindUsedCategory() throws Exception {
        int result = productDAO.findUsedCategory("d87b243d99e341d2b4af8e72bb49f6ad");
        Assert.assertEquals(2,result);
    }
}