package com.gaoxi.controller.user;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.user.*;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.redis.RedisService;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.redis.RedisServiceTemp;
import com.gaoxi.req.BatchReq;
import com.gaoxi.req.user.*;
import com.gaoxi.rsp.Result;
import com.gaoxi.util.UserUtil;
import com.gaoxi.utils.KeyGenerator;
import com.gaoxi.utils.RedisPrefixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.gaoxi.rsp.Result.newFailureResult;
import static com.gaoxi.rsp.Result.newSuccessResult;

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

    @Autowired
    private UserUtil userUtil;

    @Override
    public Result login(LoginReq loginReq, HttpServletResponse httpRsp) {

        // 登录鉴权
        UserEntity userEntity = userService.login(loginReq);

        // 登录成功
        doLoginSuccess(userEntity, httpRsp);
        return Result.newSuccessResult(userEntity);
    }

    @Override
    public Result logout(HttpServletRequest httpReq, HttpServletResponse httpRsp) {

        // 处理登出
        doLogout(httpReq, httpRsp);

        // 登出成功
        return Result.newSuccessResult();
    }

    private void doLogout(HttpServletRequest httpReq, HttpServletResponse httpRsp) {
        // 获取SessionID
        String sessionID = getSessionID(httpReq);

        // 将 SessionID-UserEntity 从Redis中移除
        // TODO 暂时存储本地
//        redisService.set(sessionID, userEntity, sessionExpireTime);
        RedisServiceTemp.userMap.remove(sessionID);

        // 将SessionID从HTTP响应头中删除
        Cookie cookie = new Cookie(sessionIdName, null);
        httpRsp.addCookie(cookie);
    }


    @Override
    public Result register(RegisterReq registerReq, HttpServletResponse httpRsp) {

        // 用户信息入库
        UserEntity userEntity = userService.register(registerReq);

        // 登录成功
        doLoginSuccess(userEntity, httpRsp);
        return newSuccessResult();
    }

    @Override
    public Result isLogin(HttpServletRequest request) {
        String sessionId = getSessionID(request);
        UserEntity userEntity = getUserEntity(sessionId);
        if (userEntity==null) {
            return newFailureResult();
        }
        return newSuccessResult(userEntity);
    }

    @Override
    public Result<List<UserEntity>> findUsers(UserQueryReq userQueryReq) {
        // 查询
        List<UserEntity> userEntityList = userService.findUsers(userQueryReq);

        // 成功
        return newSuccessResult(userEntityList);
    }

    @Override
    public Result batchUpdateUserState(BatchReq<UserStateReq> userStateReqs) {

        // 批量更新
        userService.batchUpdateUserState(userStateReqs);

        // 成功
        return newSuccessResult();
    }

    @Override
    public Result createAdminUser(AdminCreateReq adminCreateReq) {
        // 创建管理员
        userService.createAdminUser(adminCreateReq);

        // 成功
        return newSuccessResult();
    }

    @Override
    public Result<List<RoleEntity>> findRoles() {

        // 查询
        List<RoleEntity> roleEntityList = userService.findRoles();

        // 成功
        return newSuccessResult(roleEntityList);
    }

    @Override
    public Result deleteRole(String roleId) {
        // 删除
        userService.deleteRole(roleId);

        // 成功
        return newSuccessResult();
    }

    @Override
    public Result updateMenuOfRole(RoleMenuReq roleMenuReq) {
        // 更新
        userService.updateMenuOfRole(roleMenuReq);

        // 成功
        return newSuccessResult();
    }

    @Override
    public Result updatePermissionOfRole(RolePermissionReq rolePermissionReq) {
        // 更新
        userService.updatePermissionOfRole(rolePermissionReq);

        // 成功
        return newSuccessResult();
    }

    @Override
    public Result<List<PermissionEntity>> findPermissions() {
        // 查询
        List<PermissionEntity> permissionEntityList = userService.findPermissions();

        // 成功
        return newSuccessResult(permissionEntityList);
    }

    @Override
    public Result<List<MenuEntity>> findMenus() {
        // 查询
        List<MenuEntity> menuEntityList = userService.findMenus();

        // 成功
        return newSuccessResult(menuEntityList);
    }

    @Override
    public Result<List<LocationEntity>> findLocations(HttpServletRequest httpReq) {
        // 获取userId
        String userId = getUserId(httpReq);

        // 查询
        List<LocationEntity> locationEntityList = userService.findLocations(userId);

        return newSuccessResult(locationEntityList);
    }

    @Override
    public Result<String> createLocation(LocationCreateReq locationCreateReq, HttpServletRequest httpReq) {
        // 获取UserID
        String userId = getUserId(httpReq);

        // 新增
        String locationId = userService.createLocation(locationCreateReq, userId);

        // 新增成功
        return newSuccessResult(locationId);
    }

    @Override
    public Result deleteLocation(String locationId, HttpServletRequest httpReq) {
        // 获取UserID
        String userId = getUserId(httpReq);

        // 删除
        userService.deleteLocation(locationId, userId);

        // 删除成功
        return newSuccessResult();
    }

    @Override
    public Result modifyLocation(LocationUpdateReq locationUpdateReq, HttpServletRequest httpReq) {
        // 获取UserID
        String userId = getUserId(httpReq);

        // 修改收货地址
        userService.modifyLocation(locationUpdateReq, userId);

        // 修改成功
        return newSuccessResult();
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
        // TODO 暂时存储本地
//        redisService.set(sessionID, userEntity, sessionExpireTime);
        RedisServiceTemp.userMap.put(sessionID, userEntity);

        // 将SessionID存入HTTP响应头
        Cookie cookie = new Cookie(sessionIdName, sessionID);
        httpRsp.addCookie(cookie);
    }

    /**
     * 获取SessionID
     * @param request 当前的请求对象
     * @return SessionID的值
     */
    private String getSessionID(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        // 遍历所有cookie，找出SessionID
        String sessionID = null;
        if (cookies!=null && cookies.length>0) {
            for (Cookie cookie : cookies) {
                if (sessionIdName.equals(cookie.getName())) {
                    sessionID = cookie.getValue();
                    break;
                }
            }
        }

        return sessionID;
    }

    /**
     * 获取SessionID对应的用户信息
     * @param sessionID
     * @return
     */
    private UserEntity getUserEntity(String sessionID) {
        // SessionID为空
        if (StringUtils.isEmpty(sessionID)) {
            return null;
        }

        // 获取UserEntity
        // TODO 暂时存储本地
//        Object userEntity = redisService.get(sessionID);
        Object userEntity = RedisServiceTemp.userMap.get(sessionID);
        if (userEntity==null) {
            return null;
        }
        return (UserEntity) userEntity;
    }



    /**
     * 获取用户ID
     * @param httpReq HTTP请求
     * @return 用户ID
     */
    private String getUserId(HttpServletRequest httpReq) {
        UserEntity userEntity = userUtil.getUser(httpReq);
        if (userEntity == null) {
            throw new CommonBizException(ExpCodeEnum.UNLOGIN);
        }

        return userEntity.getId();
    }

}
