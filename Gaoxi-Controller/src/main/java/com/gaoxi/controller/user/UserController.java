package com.gaoxi.controller.user;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.req.user.UserQueryReq;
import com.gaoxi.rsp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:26
 */

public interface UserController {

    /**
     * 登录
     * @param loginReq 登录请求参数
     * @param httpRsp HTTP响应
     * @return 登录是否成功
     */

    public Result login(UserQueryReq loginReq, HttpServletResponse httpRsp);

}
