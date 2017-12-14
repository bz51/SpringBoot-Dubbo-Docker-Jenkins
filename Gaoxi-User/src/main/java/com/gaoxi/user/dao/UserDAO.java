package com.gaoxi.user.dao;

import com.gaoxi.entity.user.*;
import com.gaoxi.req.user.LocationUpdateReq;
import com.gaoxi.req.user.RoleMenuReq;
import com.gaoxi.req.user.RolePermissionReq;
import com.gaoxi.req.user.UserQueryReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    List<UserEntity> findUsers(@Param("userQueryReq") UserQueryReq userQueryReq);

    /**
     * 插入用户信息
     * @param userEntity 用户信息
     */
    void createUser(UserEntity userEntity);

    /**
     * 批量更新用户状态
     * @param userStateCode 用户状态
     * @param userIdList 用户ID列表
     */
    void batchUpdateUserState(@Param("userStateCode") Integer userStateCode,
                              @Param("userIdList") List<String> userIdList);

    /**
     * 查询所有的角色
     * @return 角色列表
     */
    List<RoleEntity> findRoles();

    /**
     * 删除角色
     * @param roleId 角色ID
     */
    void deleteRole(String roleId);

    /**
     * 删除角色-权限关系
     * @param roleId 角色ID
     */
    void deleteRolePermission(String roleId);

    /**
     * 删除角色-菜单关系
     * @param roleId 角色ID
     */
    void deleteRoleMenu(String roleId);

    /**
     * 插入角色-菜单关系
     * @param roleMenuReq
     */
    void insertRoleMenu(@Param("roleMenuReq") RoleMenuReq roleMenuReq);

    /**
     * 插入角色-权限关系
     * @param rolePermissionReq
     */
    void insertRolePermission(@Param("rolePermissionReq") RolePermissionReq rolePermissionReq);

    /**
     * 查询所有权限
     * @return 权限列表
     */
    List<PermissionEntity> findPermissions();

    /**
     * 查询所有菜单
     * @return 菜单列表
     */
    List<MenuEntity> findMenus();

    /**
     * 查询指定用户的地址信息
     * @param userId 用户ID
     * @return 地址信息列表
     */
    List<LocationEntity> findLocations(String userId);

    /**
     * 新增收货地址
     * @param locationEntity 收货地址
     */
    void createLocation(LocationEntity locationEntity);

    void deleteLocation(@Param("locationId") String locationId, @Param("userId") String userId);

    /**
     * 修改收货地址
     * @param locationUpdateReq 收货地址修改请求
     * @param userId 用户ID
     */
    void updateLocation(@Param("locationUpdateReq") LocationUpdateReq locationUpdateReq, @Param("userId") String userId);
}
