package com.gaoxi.req.user;

import com.gaoxi.req.AbsReq;

import java.util.List;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/4 下午4:43
 * @description 角色对应菜单的修改请求
 */
public class RoleMenuReq extends AbsReq {

    /** 角色ID */
    private String roleId;

    /**菜单ID列表*/
    private List<String> menuIdList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<String> menuIdList) {
        this.menuIdList = menuIdList;
    }

    @Override
    public String toString() {
        return "RoleMenuReq{" +
                "roleId='" + roleId + '\'' +
                ", menuIdList=" + menuIdList +
                '}';
    }
}
