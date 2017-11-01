package com.gaoxi.user.provider;

import com.gaoxi.enumeration.OrderEnum;
import com.gaoxi.req.user.UserQueryReq;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/1 下午8:55
 * @description User相关的复杂SQL语句
 */
public class UserSqlProvider {

    /**
     * 条件查询用户信息的SQL语句
     * @param userQueryReq 查询条件
     * @return SQL语句
     */
    public String findUsers(UserQueryReq userQueryReq) {
        SQL sql = new SQL().SELECT("*").FROM("sys_user");

        String id = userQueryReq.getId();
        if (StringUtils.hasText(id)) {
            sql.WHERE("id = #{id}");
        }

        String mail = userQueryReq.getMail();
        if (StringUtils.hasText(mail)) {
            sql.WHERE("mail = #{mail}");
        }

        String phone = userQueryReq.getPhone();
        if (StringUtils.hasText(phone)) {
            sql.WHERE("phone = #{phone}");
        }

        String startTime = userQueryReq.getRegisterTimeStart();
        String endTime = userQueryReq.getRegisterTimeEnd();
        if (startTime!=null && endTime!=null) {
            sql.WHERE("register_time BETWEEN #{startTime} AND #{endTime}");
        }
        else if (startTime==null) {
            sql.WHERE("register_time BETWEEN '1970-01-01 00:00:00' AND #{endTime}");
        }
        else if (endTime==null) {
            sql.WHERE("register_time > #{startTime}");
        }

        String roleId = userQueryReq.getRoleId();
        if (StringUtils.hasText(roleId)) {
            sql.WHERE("roleId = #{roleId}");
        }

        String username = userQueryReq.getUsername();
        if (StringUtils.hasText(username)) {
            sql.WHERE("username LIKE #{username}");
        }

        Integer userState = userQueryReq.getUserState();
        if (userState!=null) {
            sql.WHERE("user_state = #{userState}");
        }

        Integer userType = userQueryReq.getUserType();
        if (userType!=null) {
            sql.WHERE("user_type = #{userType}");
        }

        Integer orderByRegisterTimeCode = userQueryReq.getOrderByRegisterTime();
        if (orderByRegisterTimeCode!=null) {
            sql.ORDER_BY("register_time " + OrderEnum.getMsgByCode(orderByRegisterTimeCode));
        }

        return sql.toString();
    }

}
