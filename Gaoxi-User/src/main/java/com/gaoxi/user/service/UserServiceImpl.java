package com.gaoxi.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.facade.user.UserService;
import com.gaoxi.req.user.UserQueryReq;
import com.gaoxi.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public List<UserEntity> findUsers(UserQueryReq userQueryReq) {
        return userDAO.findUsers(userQueryReq);
    }
}
