package com.gaoxi.controller.user;

import com.gaoxi.annotation.Login;
import com.gaoxi.annotation.Permission;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.req.user.UserQueryReq;
import com.gaoxi.rsp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @GetMapping("/login")
    public Result login(UserQueryReq loginReq, HttpServletResponse httpRsp);

    /**
     * 注册
     * @param userEntity 用户信息
     * @param httpRsp HTTP响应
     * @return 注册是否成功
     */
    @PostMapping("/register")
    public Result register(UserEntity userEntity, HttpServletResponse httpRsp);


    /**
     * 查询用户信息
     * @param userQueryReq 用户查询请求
     * @return 用户查询结果
     */
    @GetMapping("/user")
    @Login
    @Permission("user:query")
    public Result<List<UserEntity>> findUsers(UserQueryReq userQueryReq);

    public Result updateUserState();



}
