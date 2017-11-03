package com.gaoxi.controller.user;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.redis.RedisUtilsFacade;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.req.user.UserQueryReq;
import com.gaoxi.rsp.Result;
import com.gaoxi.utils.KeyGenerator;
import com.gaoxi.utils.RedisPrefixUtil;
import com.sun.xml.internal.ws.client.ResponseContextReceiver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private RedisUtilsFacade redisUtils;

    /** Session有效时间 */
    @Value("${session.expireTime}")
    private long sessionExpireTime;

    /** HTTP Response中Session ID 的名字 */
    @Value("${session.SessionIdName}")
    private String sessionIdName;

    @Override
    public Result login(UserQueryReq loginReq, HttpServletResponse httpRsp) {

        // 校验参数
        checkParam(loginReq);

        // 查询用户
        List<UserEntity> userEntityList = userService.findUsers(loginReq);

        // 登录失败
        if (CollectionUtils.isEmpty(userEntityList)) {
            throw new CommonBizException(ExpCodeEnum.LOGIN_FAIL);
        }

        // 登录成功
        doLoginSuccess(userEntityList, httpRsp);
        return Result.newSuccessResult();
    }

    /**
     * 处理登录成功
     * @param userEntityList 用户信息
     * @param httpRsp HTTP响应参数
     */
    private void doLoginSuccess(List<UserEntity> userEntityList, HttpServletResponse httpRsp) {
        // 生成SessionID
        String sessionID = RedisPrefixUtil.SessionID_Prefix + KeyGenerator.getKey();

        // 获取UserEntity
        UserEntity userEntity = userEntityList.get(0);

        // 将 SessionID-UserEntity 存入Redis
        redisUtils.set(sessionID, userEntity, sessionExpireTime);

        // 将SessionID存入HTTP响应头
        Cookie cookie = new Cookie(sessionIdName, sessionID);
        httpRsp.addCookie(cookie);
    }


    /**
     * 参数校验
     * @param loginReq
     */
    private void checkParam(UserQueryReq loginReq) {
        // 密码不能为空
        if (StringUtils.isEmpty(loginReq.getPassword())) {
            throw new CommonBizException(ExpCodeEnum.PASSWD_NULL);
        }

        // 手机、mail、用户名 至少填一个
        if (StringUtils.isEmpty(loginReq.getUsername()) &&
                StringUtils.isEmpty(loginReq.getMail()) &&
                StringUtils.isEmpty(loginReq.getPhone())) {
            throw new CommonBizException(ExpCodeEnum.AUTH_NULL);
        }
    }
}
