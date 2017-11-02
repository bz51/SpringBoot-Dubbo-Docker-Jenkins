package com.gaoxi.init;

import com.gaoxi.enumeration.HttpMethodEnum;

import java.io.Serializable;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 上午11:05
 * @description 接口访问权限的实体类
 */
public class AccessAuthEntity implements Serializable {

    private String url;
    private String methodName;
    private HttpMethodEnum httpMethodEnum;
    private boolean isLogin;
    private String permission;

    public AccessAuthEntity() {
    }

    public AccessAuthEntity(String url, String methodName, HttpMethodEnum httpMethodEnum, boolean isLogin, String permission) {
        this.url = url;
        this.methodName = methodName;
        this.httpMethodEnum = httpMethodEnum;
        this.isLogin = isLogin;
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public HttpMethodEnum getHttpMethodEnum() {
        return httpMethodEnum;
    }

    public void setHttpMethodEnum(HttpMethodEnum httpMethodEnum) {
        this.httpMethodEnum = httpMethodEnum;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
