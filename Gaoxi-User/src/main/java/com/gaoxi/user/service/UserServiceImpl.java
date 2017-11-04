package com.gaoxi.user.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.user.UserStateEnum;
import com.gaoxi.enumeration.user.UserTypeEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.req.user.LoginReq;
import com.gaoxi.req.user.RegisterReq;
import com.gaoxi.req.user.UserQueryReq;
import com.gaoxi.user.dao.UserDAO;
import com.gaoxi.utils.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 上午10:06
 * @description 用户相关操作
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /** 用户表主键的前缀 */
    private static final String USER_KEY_PREFIX = "USER";

    @Override
    public UserEntity login(LoginReq loginReq) {

        // 校验参数
        checkParam(loginReq);

        // 创建用户查询请求
        UserQueryReq userQueryReq = buildUserQueryReq(loginReq);

        // 查询用户
        List<UserEntity> userEntityList = userDAO.findUsers(userQueryReq);

        // 查询失败
        if (CollectionUtils.isEmpty(userEntityList)) {
            throw new CommonBizException(ExpCodeEnum.LOGIN_FAIL);
        }

        // 查询成功
        return userEntityList.get(0);
    }

    /**
     * 构造用户查询对象
     * @param loginReq 登录请求
     * @return 用户查询请求
     */
    private UserQueryReq buildUserQueryReq(LoginReq loginReq) {
        UserQueryReq userQueryReq = new UserQueryReq();
        // 设置密码
        userQueryReq.setPassword(loginReq.getPassword());
        // 设置用户名
        if (StringUtils.isNotEmpty(loginReq.getUsername())) {
            userQueryReq.setUsername(loginReq.getUsername());
        }
        // 设置邮箱
        if (StringUtils.isNotEmpty(loginReq.getMail())) {
            userQueryReq.setMail(loginReq.getMail());
        }
        // 设置手机号
        if (StringUtils.isNotEmpty(loginReq.getPhone())) {
            userQueryReq.setPhone(loginReq.getPhone());
        }

        return userQueryReq;
    }

    @Override
    public List<UserEntity> findUsers(UserQueryReq userQueryReq) {
        return userDAO.findUsers(userQueryReq);
    }

    @Override
    public UserEntity register(RegisterReq registerReq) {

        // 校验参数
        checkParam(registerReq);

        // 构造UserEntity
        UserEntity userEntity = buildUserEntity(registerReq);

        // 用户信息入库
        userDAO.createUser(userEntity);

        // 入库成功
        return userEntity;
    }

    /**
     * 构造UserEntity
     * @param registerReq 用户注册请求
     * @return UserEntity对象
     */
    private UserEntity buildUserEntity(RegisterReq registerReq) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(generateKey());
        userEntity.setUsername(registerReq.getUsername());
        userEntity.setPassword(registerReq.getPassword());
        userEntity.setLicencePic(registerReq.getLicencePic());
        userEntity.setMail(registerReq.getMail());
        userEntity.setPhone(registerReq.getPhone());
        userEntity.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setUserTypeEnum(UserTypeEnum.getEnumByCode(registerReq.getUserType()));

        // 普通用户无需认证
        userEntity.setUserStateEnum(UserStateEnum.ON);
        // 企业用户需认证
        if (userEntity.getUserTypeEnum() == UserTypeEnum.Company) {
            userEntity.setUserStateEnum(UserStateEnum.OFF);
        }

        return userEntity;
    }

    /**
     * 生成User主键('USER'+UUID)
     * @return 主键
     */
    private String generateKey() {
        return USER_KEY_PREFIX + KeyGenerator.getKey();
    }

    /**
     * 校验参数
     * @param registerReq 注册请求
     */
    private void checkParam(RegisterReq registerReq) {
        // 密码不能为空
        if (StringUtils.isEmpty(registerReq.getPassword())) {
            throw new CommonBizException(ExpCodeEnum.PASSWD_NULL);
        }

        // 手机号不能为空
        if (StringUtils.isEmpty(registerReq.getPhone())) {
            throw new CommonBizException(ExpCodeEnum.PHONE_NULL);
        }

        // mail不能为空
        if (StringUtils.isEmpty(registerReq.getMail())) {
            throw new CommonBizException(ExpCodeEnum.MAIL_NULL);
        }

        // 用户类别不能为空
        if (registerReq.getUserType() == null) {
            throw new CommonBizException(ExpCodeEnum.USERTYPE_NULL);
        }

        // 企业用户
        if (registerReq.getUserType() == UserTypeEnum.Company.getCode()) {
            // 营业执照不能为空
            if (StringUtils.isEmpty(registerReq.getLicencePic())) {
                throw new CommonBizException(ExpCodeEnum.LICENCE_NULL);
            }

            // 企业名称不能为空
            if (StringUtils.isEmpty(registerReq.getUsername())) {
                throw new CommonBizException(ExpCodeEnum.COMPANYNAME_NULL);
            }
        }
    }


    /**
     * 参数校验
     * @param loginReq
     */
    private void checkParam(LoginReq loginReq) {
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
