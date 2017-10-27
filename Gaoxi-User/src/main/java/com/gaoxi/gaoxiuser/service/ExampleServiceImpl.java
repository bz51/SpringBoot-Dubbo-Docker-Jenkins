package com.gaoxi.gaoxiuser.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class ExampleServiceImpl implements ExampleService {

    @Override
    public String hello(){
        return "Hello World!";
    }
}
