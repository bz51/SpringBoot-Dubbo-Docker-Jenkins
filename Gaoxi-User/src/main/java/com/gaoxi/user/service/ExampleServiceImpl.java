package com.gaoxi.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.facade.user.ExampleService;

@Service(version = "1.0.0")
public class ExampleServiceImpl implements ExampleService {

    @Override
    public String hello(){
        return "Hello World!";
    }
}
