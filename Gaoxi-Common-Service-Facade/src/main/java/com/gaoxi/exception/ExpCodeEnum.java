package com.gaoxi.exception;

import com.gaoxi.utils.ExpPrefixUtil;

import static com.gaoxi.utils.ExpPrefixUtil.*;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:37
 * 全局的异常状态码 和 异常描述
 *
 * PS:异常码一共由5位组成，前两位为固定前缀，请参考{@link com.gaoxi.utils.ExpPrefixUtil}
 */
public enum ExpCodeEnum {

    /** 通用异常 */
    UNKNOW_ERROR(ComExpPrefix + "000", "未知异常"),

    /** User模块异常 */
    USERNAME_NULL(UserExpPrefix + "000", "用户名为空"),
    PASSWD_NULL(UserExpPrefix + "001", "密码为空"),
    AUTH_NULL(UserExpPrefix + "002", "手机、电子邮件、用户名 至少填一个"),
    LOGIN_FAIL(UserExpPrefix + "003", "登录失败"),
    UNLOGIN(UserExpPrefix + "004", "尚未登录"),
    NO_PERMISSION(UserExpPrefix + "005", "没有权限"),

    /** Product模块异常 */
    PRODUCT_NAME_NULL(ProdExpPrefix + "000", "产品名称为空"),

    /** Order模块异常 */
    STOCK_LOW(OrderExpPrefix + "000", "库存不足"),

    /** Analysis模块异常 */
    PARAM_NULL(AnlsExpPrefix + "000", "参数为空");

    private String code;
    private String message;

    private ExpCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
