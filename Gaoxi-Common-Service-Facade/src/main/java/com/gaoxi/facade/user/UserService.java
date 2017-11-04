package com.gaoxi.facade.user;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.req.user.LoginReq;
import com.gaoxi.req.user.RegisterReq;
import com.gaoxi.req.user.UserQueryReq;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 上午10:06
 * @description
 */
public interface UserService {

    public UserEntity login(LoginReq loginReq);

    public List<UserEntity> findUsers(UserQueryReq userQueryReq);

    UserEntity register(RegisterReq registerReq);
}
