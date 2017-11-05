package com.gaoxi.user.service;

import com.alibaba.dubbo.common.utils.Assert;
import com.gaoxi.entity.user.MenuEntity;
import com.gaoxi.entity.user.PermissionEntity;
import com.gaoxi.entity.user.RoleEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.user.UserTypeEnum;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.req.BatchReq;
import com.gaoxi.req.user.*;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void login() throws Exception {
        LoginReq loginReq = new LoginReq();
        loginReq.setUsername("chai");
        loginReq.setPassword("123");
        UserEntity userEntity = userService.login(loginReq);
        System.out.println(userEntity);
    }

    @Test
    public void findUsers() throws Exception {
        UserQueryReq userQueryReq = new UserQueryReq();
        userQueryReq.setUsername("cha");
        userQueryReq.setOrderByRegisterTime(1);
        List<UserEntity> userEntityList = userService.findUsers(userQueryReq);
        System.out.println(userEntityList);
    }

    @Test
    public void registerPerson() throws Exception {
        // 插入普通用户
        RegisterReq registerReq = new RegisterReq();
        registerReq.setPhone("15251896025");
        registerReq.setPassword("123");
        registerReq.setMail("xxxx@qq.com");
        registerReq.setUserType(UserTypeEnum.Person.getCode());
        registerReq.setLicencePic("xxx.jpg");
        UserEntity userEntity = userService.register(registerReq);
        System.out.println(userEntity);
    }

    @Test
    public void registerCompany() throws Exception {
        // 插入企业用户
        RegisterReq registerReq = new RegisterReq();
        registerReq.setPhone("15251896026");
        registerReq.setPassword("123");
        registerReq.setMail("xxxx@qq.com");
        registerReq.setUsername("高喜");
        registerReq.setUserType(UserTypeEnum.Company.getCode());
        registerReq.setLicencePic("xxx.jpg");
        UserEntity userEntity = userService.register(registerReq);
        System.out.println(userEntity);
    }

    @Test
    public void batchUpdateUserState() throws Exception {
        BatchReq<UserStateReq> batchReq = new BatchReq<>();
        List<UserStateReq> userStateReqList = Lists.newArrayList();
        userStateReqList.add(new UserStateReq("1",0));
        userStateReqList.add(new UserStateReq("15251896025",0));
        userStateReqList.add(new UserStateReq("USERaa4bc3e513114d5799888b0a292c3a14",1));
        userStateReqList.add(new UserStateReq("XXXX",1));
        batchReq.setReqList(userStateReqList);
        userService.batchUpdateUserState(batchReq);
    }

    @Test
    public void createAdminUser() throws Exception {
        AdminCreateReq adminCreateReq = new AdminCreateReq();
        adminCreateReq.setUsername("chaimmadmin");
        adminCreateReq.setPassword("123");
        adminCreateReq.setPhone("110");
        adminCreateReq.setRoleId("3");

        userService.createAdminUser(adminCreateReq);
    }

    @Test
    public void findRoles() throws Exception {
        List<RoleEntity> roleEntityList = userService.findRoles();
        System.out.println(roleEntityList);
    }

    @Test
    public void deleteRole() throws Exception {
        userService.deleteRole("2");
    }

    @Test
    public void updateMenuOfRole() throws Exception {
        RoleMenuReq roleMenuReq = new RoleMenuReq();
        roleMenuReq.setRoleId("1");
        roleMenuReq.setMenuIdList(Arrays.asList("3","4"));

        userService.updateMenuOfRole(roleMenuReq);
    }

    @Test
    public void updatePermissionOfRole() throws Exception {
        RolePermissionReq rolePermissionReq = new RolePermissionReq();
        rolePermissionReq.setRoleId("2");
        rolePermissionReq.setPermissionIdList(Arrays.asList("2","3","4","5","6"));

        userService.updatePermissionOfRole(rolePermissionReq);
    }

    @Test
    public void findPermissions() throws Exception {
        List<PermissionEntity> permissionEntityList = userService.findPermissions();
        System.out.println(permissionEntityList);
    }

    @Test
    public void findMenus() throws Exception {
        List<MenuEntity> menuEntityList = userService.findMenus();
        System.out.println(menuEntityList);
    }

}