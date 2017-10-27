package com.gaoxi.gaoxicontroller.controller.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.gaoxicontroller.controller.ExampleController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 大闲人柴毛毛
 */
@RestController
public class ExampleControllerImpl implements ExampleController {

//    @Reference(version = "1.0.0")
    private ExampleService exampleService;

    @Override
    @RequestMapping("hello")
    public String hello() {
//        return exampleService.hello();
        return "hello";
    }
}
