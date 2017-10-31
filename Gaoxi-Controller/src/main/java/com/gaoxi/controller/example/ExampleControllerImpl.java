package com.gaoxi.controller.example;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.rsp.Result;
import com.gaoxi.entity.user.ArticleEntity;
import com.gaoxi.facade.user.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@ResponseBody
public class ExampleControllerImpl implements ExampleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "1.0.0")
    private ExampleService exampleService;

    @Override
    @RequestMapping("hello")
    public Result<String> hello() {
        return Result.newSuccessResult(exampleService.hello());
    }

    @Override
    @RequestMapping("article/{userId}")
    public Result<List<ArticleEntity>> findArticlesByUserId(@PathVariable("userId") String userId) {
        return Result.newSuccessResult(exampleService.findArticlesByUserId(userId));
    }
}
