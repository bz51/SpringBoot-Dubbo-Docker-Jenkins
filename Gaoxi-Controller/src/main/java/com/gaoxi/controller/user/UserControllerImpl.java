package com.gaoxi.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.user.MenuEntity;
import com.gaoxi.entity.user.PermissionEntity;
import com.gaoxi.entity.user.RoleEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.facade.redis.RedisService;
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

    @Reference(version = "1.0.0")
    private UserService userService;

    @Reference(version = "1.0.0")
    private RedisService redisService;

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
        // 查询
        List<UserEntity> userEntityList = userService.findUsers(userQueryReq);

        // 成功
        return Result.newSuccessResult(userEntityList);
    }

    @Override
    public Result batchUpdateUserState(BatchReq<UserStateReq> userStateReqs) {
        // 批量更新
        userService.batchUpdateUserState(userStateReqs);

        // 成功
        return Result.newSuccessResult();
    }

    @Override
    public Result createAdminUser(AdminCreateReq adminCreateReq) {
        // 创建管理员
        userService.createAdminUser(adminCreateReq);

        // 成功
        return Result.newSuccessResult();
    }

    @Override
    public Result<List<RoleEntity>> findRoles() {

        // 查询
        List<RoleEntity> roleEntityList = userService.findRoles();

        // 成功
        return Result.newSuccessResult(roleEntityList);
    }

    @Override
    public Result deleteRole(String roleId) {
        // 删除
        userService.deleteRole(roleId);

        // 成功
        return Result.newSuccessResult();
    }

    @Override
    public Result updateMenuOfRole(RoleMenuReq roleMenuReq) {
        // 更新
        userService.updateMenuOfRole(roleMenuReq);

        // 成功
        return Result.newSuccessResult();
    }

    @Override
    public Result updatePermissionOfRole(RolePermissionReq rolePermissionReq) {
        // 更新
        userService.updatePermissionOfRole(rolePermissionReq);

        // 成功
        return Result.newSuccessResult();
    }

    @Override
    public Result<List<PermissionEntity>> findPermissions() {
        // 查询
        List<PermissionEntity> permissionEntityList = userService.findPermissions();

        // 成功
        return Result.newSuccessResult(permissionEntityList);
    }

    @Override
    public Result<List<MenuEntity>> findMenus() {
        // 查询
        List<MenuEntity> menuEntityList = userService.findMenus();

        // 成功
        return Result.newSuccessResult(menuEntityList);
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
        redisService.set(sessionID, userEntity, sessionExpireTime);

        // 将SessionID存入HTTP响应头
        Cookie cookie = new Cookie(sessionIdName, sessionID);
        httpRsp.addCookie(cookie);
    }
}
