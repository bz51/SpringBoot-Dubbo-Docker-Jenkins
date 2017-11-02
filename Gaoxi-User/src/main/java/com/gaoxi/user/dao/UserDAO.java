package com.gaoxi.user.dao;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.req.user.UserQueryReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 下午8:37
 * @description 用户相关DAO
 */
@Mapper
public interface UserDAO {

    /**
     * 查询用户信息
     * @param userQueryReq 查询请求
     * @return 查询结果
     */
    public List<UserEntity> findUsers(UserQueryReq userQueryReq);

}
