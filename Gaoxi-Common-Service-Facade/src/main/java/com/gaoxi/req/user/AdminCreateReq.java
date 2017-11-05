package com.gaoxi.req.user;

import com.gaoxi.entity.user.RoleEntity;
import com.gaoxi.req.AbsReq;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/4 下午4:19
 * @description 创建管理员的请求
 */
public class AdminCreateReq extends AbsReq {

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 手机号 */
    private String phone;

    /** 角色ID */
    private String roleId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "AdminCreateReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
