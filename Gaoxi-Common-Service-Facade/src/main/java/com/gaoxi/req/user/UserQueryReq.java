package com.gaoxi.req.user;

import com.gaoxi.req.QueryReq;

import java.sql.Timestamp;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 下午8:45
 * @description 用户查询请求
 */
public class UserQueryReq extends QueryReq {

    /** 主键 */
    private String id;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 手机号 */
    private String phone;

    /** 邮箱 */
    private String mail;

    /** 注册时间(起始) */
    private String registerTimeStart;
    /** 注册时间(结束) */
    private String registerTimeEnd;

    /** 用户类型 {@link com.gaoxi.enumeration.user.UserTypeEnum} */
    private Integer userType;

    /** 账号状态 {@link com.gaoxi.enumeration.user.UserStateEnum } */
    private Integer userState;

    /** 用户角色ID */
    private String roleId;


    /** 根据注册时间排序 {@link com.gaoxi.enumeration.OrderEnum} */
    private Integer orderByRegisterTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRegisterTimeStart() {
        return registerTimeStart;
    }

    public void setRegisterTimeStart(String registerTimeStart) {
        this.registerTimeStart = registerTimeStart;
    }

    public String getRegisterTimeEnd() {
        return registerTimeEnd;
    }

    public void setRegisterTimeEnd(String registerTimeEnd) {
        this.registerTimeEnd = registerTimeEnd;
    }

    public Integer getOrderByRegisterTime() {
        return orderByRegisterTime;
    }

    public void setOrderByRegisterTime(Integer orderByRegisterTime) {
        this.orderByRegisterTime = orderByRegisterTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "UserQueryReq{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", registerTimeStart='" + registerTimeStart + '\'' +
                ", registerTimeEnd='" + registerTimeEnd + '\'' +
                ", userType=" + userType +
                ", userState=" + userState +
                ", roleId='" + roleId + '\'' +
                ", orderByRegisterTime=" + orderByRegisterTime +
                ", page=" + page +
                ", numPerPage=" + numPerPage +
                '}';
    }

}
