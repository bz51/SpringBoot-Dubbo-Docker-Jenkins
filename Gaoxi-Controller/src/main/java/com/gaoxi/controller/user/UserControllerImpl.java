package com.gaoxi.controller.user;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.user.MenuEntity;
import com.gaoxi.entity.user.PermissionEntity;
import com.gaoxi.entity.user.RoleEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.redis.RedisUtils;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.req.BatchReq;
import com.gaoxi.req.user.*;
import com.gaoxi.rsp.Result;
import com.gaoxi.utils.KeyGenerator;
import com.gaoxi.utils.RedisPrefixUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:26
 */
@RestController
public class UserControllerImpl implements UserController {

    @Reference
    private UserService userService;

    @Reference
    private RedisUtils redisUtils;

    /** Session有效时间 */
    @Value("${session.expireTime}")
    private long sessionExpireTime;

    /** HTTP Response中Session ID 的名字 */
    @Value("${session.SessionIdName}")
    private String sessionIdName;

    @Override
    public Result login(LoginReq loginReq, HttpServletResponse httpRsp) {

        // 登录鉴权
        UserEntity userEntity = userService.login(loginReq);

        // 登录成功
        doLoginSuccess(userEntity, httpRsp);
        return Result.newSuccessResult();
    }


    @Override
    public Result register(RegisterReq registerReq, HttpServletResponse httpRsp) {

        // 用户信息入库
        UserEntity userEntity = userService.register(registerReq);

        // 登录成功
        doLoginSuccess(userEntity, httpRsp);
        return Result.newSuccessResult();
    }

    @Override
    public Result<List<UserEntity>> findUsers(UserQueryReq userQueryReq) {
        return null;
    }

    @Override
    public Result batchUpdateUserState(BatchReq<UserStateReq> userStateReqs) {
        return null;
    }

    @Override
    public Result createAdminUser(AdminCreateReq adminCreateReq) {
        return null;
    }

    @Override
    public Result<List<RoleEntity>> findRoles() {
        return null;
    }

    @Override
    public Result deleteRole(String roleId) {
        return null;
    }

    @Override
    public Result updateMenuOfRole(RoleMenuReq roleMenuReq) {
        return null;
    }

    @Override
    public Result updatePermissionOfRole(RolePermissionReq rolePermissionReq) {
        return null;
    }

    @Override
    public Result<List<PermissionEntity>> findPermissions() {
        return null;
    }

    @Override
    public Result<List<MenuEntity>> findMenus() {
        return null;
    }

    /**
     * 处理登录成功
     * @param userEntity 用户信息
     * @param httpRsp HTTP响应参数
     */
    private void doLoginSuccess(UserEntity userEntity, HttpServletResponse httpRsp) {
        // 生成SessionID
        String sessionID = RedisPrefixUtil.SessionID_Prefix + KeyGenerator.getKey();

        // 将 SessionID-UserEntity 存入Redis
        redisUtils.set(sessionID, userEntity, sessionExpireTime);

        // 将SessionID存入HTTP响应头
        Cookie cookie = new Cookie(sessionIdName, sessionID);
        httpRsp.addCookie(cookie);
    }
}
