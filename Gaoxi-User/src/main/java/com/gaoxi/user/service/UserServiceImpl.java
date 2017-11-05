package com.gaoxi.user.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.entity.user.MenuEntity;
import com.gaoxi.entity.user.PermissionEntity;
import com.gaoxi.entity.user.RoleEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.user.UserStateEnum;
import com.gaoxi.enumeration.user.UserTypeEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.req.BatchReq;
import com.gaoxi.req.user.*;
import com.gaoxi.user.dao.UserDAO;
import com.gaoxi.utils.KeyGenerator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 上午10:06
 * @description 用户相关操作
 */
@Service
@org.springframework.stereotype.Service
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

        // TODO 增加判断： 用户名、手机、邮箱 均不能重复

        // 用户信息入库
        userDAO.createUser(userEntity);

        // 入库成功
        return userEntity;
    }

    @Override
    public void batchUpdateUserState(BatchReq<UserStateReq> userStateReqs) {
        // 参数校验
        checkParam(userStateReqs);

        // 按照userState将userId分组
        Map<Integer, List<String>> userStateMap = groupUserIdByUserState(userStateReqs);

        // 批量更新
        if (!userStateMap.isEmpty()) {
            for (Integer userStateCode : userStateMap.keySet()) {
                userDAO.batchUpdateUserState(userStateCode, userStateMap.get(userStateCode));
            }
        }
    }

    @Override
    public void createAdminUser(AdminCreateReq adminCreateReq) {
        // 参数校验
        checkParam(adminCreateReq);

        // 构造UserEntity
        UserEntity userEntity = buildUserEntity(adminCreateReq);

        // 用户信息入库
        userDAO.createUser(userEntity);
    }

    @Override
    public List<RoleEntity> findRoles() {
        return userDAO.findRoles();
    }

    @Override
    public void deleteRole(String roleId) {
        // 参数校验
        checkParam(roleId);

        // 删除角色
        userDAO.deleteRole(roleId);

        // 删除角色-权限关系
        userDAO.deleteRolePermission(roleId);

        // 删除角色-菜单关系
        userDAO.deleteRoleMenu(roleId);
    }

    @Override
    public void updateMenuOfRole(RoleMenuReq roleMenuReq) {
        // 参数校验
        checkParam(roleMenuReq);

        // 删除该角色下所有的菜单
        userDAO.deleteRoleMenu(roleMenuReq.getRoleId());

        // 插入该角色下的新菜单
        userDAO.insertRoleMenu(roleMenuReq);
    }

    @Override
    public void updatePermissionOfRole(RolePermissionReq rolePermissionReq) {
        // 参数校验
        checkParam(rolePermissionReq);

        // 删除该角色下的所有权限
        userDAO.deleteRolePermission(rolePermissionReq.getRoleId());

        // 插入该角色下的新权限
        userDAO.insertRolePermission(rolePermissionReq);
    }

    @Override
    public List<PermissionEntity> findPermissions() {
        return userDAO.findPermissions();
    }

    @Override
    public List<MenuEntity> findMenus() {
        return userDAO.findMenus();
    }

    private void checkParam(RolePermissionReq rolePermissionReq) {
        // 参数不能为空
        if (rolePermissionReq==null) {
            throw new CommonBizException(ExpCodeEnum.PARAM_NULL);
        }

        // roleId不能为空
        if (StringUtils.isEmpty(rolePermissionReq.getRoleId())) {
            throw new CommonBizException(ExpCodeEnum.ROLEID_NULL);
        }

        // 权限Id列表不能为空
        if (CollectionUtils.isEmpty(rolePermissionReq.getPermissionIdList())) {
            throw new CommonBizException(ExpCodeEnum.PERMISSIONIDLIST_NULL);
        }
    }


    private void checkParam(RoleMenuReq roleMenuReq) {
        // 参数不能为空
        if (roleMenuReq==null) {
            throw new CommonBizException(ExpCodeEnum.PARAM_NULL);
        }

        // roleId不能为空
        if (StringUtils.isEmpty(roleMenuReq.getRoleId())) {
            throw new CommonBizException(ExpCodeEnum.ROLEID_NULL);
        }

        // menuId列表不能为空
        if (CollectionUtils.isEmpty(roleMenuReq.getMenuIdList())) {
            throw new CommonBizException(ExpCodeEnum.MENUIDLIST_NULL);
        }
    }

    private void checkParam(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new CommonBizException(ExpCodeEnum.PARAM_NULL);
        }
    }

    /**
     * 构造UserEntity对象
     * @param adminCreateReq 创建管理员的请求
     * @return UserEntity对象
     */
    private UserEntity buildUserEntity(AdminCreateReq adminCreateReq) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(generateKey());
        userEntity.setUsername(adminCreateReq.getUsername());
        userEntity.setUserStateEnum(UserStateEnum.ON);
        userEntity.setUserTypeEnum(UserTypeEnum.ADMIN);
        userEntity.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setPassword(adminCreateReq.getPassword());
        userEntity.setPhone(adminCreateReq.getPhone());

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(adminCreateReq.getRoleId());
        userEntity.setRoleEntity(roleEntity);

        return userEntity;
    }

    private void checkParam(AdminCreateReq adminCreateReq) {
        // 密码不能为空
        if (StringUtils.isEmpty(adminCreateReq.getPassword())) {
           throw new CommonBizException(ExpCodeEnum.PASSWD_NULL);
        }

        // 用户名不能为空
        if (StringUtils.isEmpty(adminCreateReq.getUsername())) {
           throw new CommonBizException(ExpCodeEnum.USERNAME_NULL);
        }

        // 电话不能为空
        if (StringUtils.isEmpty(adminCreateReq.getPhone())) {
            throw new CommonBizException(ExpCodeEnum.PHONE_NULL);
        }

        // 角色不能为空
        if (adminCreateReq.getRoleId() == null) {
            throw new CommonBizException(ExpCodeEnum.ROLE_NULL);
        }

    }

    /**
     * 根据userState将userId分组
     * @param userStateReqs 修改用户状态的请求
     * @return 分组后结果（key：用户状态、value：该状态下对应的userid列表）
     */
    private Map<Integer, List<String>> groupUserIdByUserState(BatchReq<UserStateReq> userStateReqs) {
        // 创建结果集
        Map<Integer, List<String>> userStateMap = Maps.newHashMap();

        // 遍历UserStateEnum
        if (UserStateEnum.values().length > 0) {
            for (UserStateEnum userStateEnum : UserStateEnum.values()) {
                // 获取当前用户状态下的所有userid
                List<String> userIdList = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(userStateReqs.getReqList())) {
                    for (UserStateReq userStateReq : userStateReqs.getReqList()) {
                        if (userStateReq.getUserState() == userStateEnum.getCode()) {
                            userIdList.add(userStateReq.getUserId());
                        }
                    }
                    userStateMap.put(userStateEnum.getCode(), userIdList);
                }
            }
        }

        return userStateMap;
    }

    private void checkParam(BatchReq<UserStateReq> userStateReqs) {
        if (userStateReqs == null ||
                CollectionUtils.isEmpty(userStateReqs.getReqList())) {
            throw new CommonBizException(ExpCodeEnum.PARAM_NULL);
        }
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
