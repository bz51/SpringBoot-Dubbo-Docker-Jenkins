package com.gaoxi.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.dto.Result;
import com.gaoxi.facade.user.ExampleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:26
 */
@RestController("/user")
@ResponseBody
public class UserControllerImpl implements UserController {

    @Reference(version = "1.0.0")
    private ExampleService exampleService;

    @Override
    @RequestMapping("/hello")
    public Result<String> hello() {
        return Result.newSuccessResult(exampleService.hello());
    }

}
