package com.gaoxi.controller.example;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.dto.Result;
import com.gaoxi.facade.user.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExampleControllerImpl implements ExampleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "1.0.0")
    private ExampleService exampleService;

    @Override
    @RequestMapping("hello")
    public Result<String> hello() {
        return Result.newSuccessResult(exampleService.hello());
    }
}
