package com.gaoxi.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.entity.user.ArticleEntity;
import com.gaoxi.facade.user.ExampleService;
import com.gaoxi.user.dao.DemoDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Service(version = "1.0.0")
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private DemoDAO demoDAO;

    @Override
    public String hello(){
        return "Hello World!";
    }

    @Override
    public List<ArticleEntity> findArticlesByUserId(String userId) {
        return demoDAO.findArticlesByUserId(userId);
    }


}
