package com.gaoxi.entity.user;

import java.io.Serializable;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/30 下午6:11
 */
public class UserEntity implements Serializable {
    private String id;
    private String username;
    private String password;

    // TODO UserEntity仍需完善……

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
