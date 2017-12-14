package com.gaoxi.exception;

import com.gaoxi.utils.ExpPrefixUtil;

import java.io.Serializable;

import static com.gaoxi.utils.ExpPrefixUtil.*;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:37
 * 全局的异常状态码 和 异常描述
 *
 * PS:异常码一共由5位组成，前两位为固定前缀，请参考{@link com.gaoxi.utils.ExpPrefixUtil}
 */
public enum ExpCodeEnum implements Serializable {

    /** 通用异常 */
    UNKNOW_ERROR(ComExpPrefix + "000", "未知异常"),
    ERROR_404(ComExpPrefix + "001", "没有该接口"),
    PARAM_NULL(ComExpPrefix + "002", "参数为空"),
    NO_REPEAT(ComExpPrefix + "003", "请勿重复提交"),
    SESSION_NULL(ComExpPrefix + "004", "请求头中SessionId不存在"),
    HTTP_REQ_METHOD_ERROR(ComExpPrefix + "005", "HTTP请求方法不正确"),
    JSONERROR(ComExpPrefix + "006", "JSON解析异常"),

    /** User模块异常 */
    USERNAME_NULL(UserExpPrefix + "000", "用户名为空"),
    PASSWD_NULL(UserExpPrefix + "001", "密码为空"),
    AUTH_NULL(UserExpPrefix + "002", "手机、电子邮件、用户名 至少填一个"),
    LOGIN_FAIL(UserExpPrefix + "003", "登录失败"),
    UNLOGIN(UserExpPrefix + "004", "尚未登录"),
    NO_PERMISSION(UserExpPrefix + "005", "没有权限"),
    PHONE_NULL(UserExpPrefix + "006", "手机号为空"),
    MAIL_NULL(UserExpPrefix + "007", "电子邮件为空"),
    USERTYPE_NULL(UserExpPrefix + "008", "用户类别为空"),
    LICENCE_NULL(UserExpPrefix + "009", "营业执照为空"),
    COMPANYNAME_NULL(UserExpPrefix + "010", "企业名称为空"),
    ROLE_NULL(UserExpPrefix + "011", "角色为空"),
    ROLEID_NULL(UserExpPrefix + "012", "roleId为空"),
    MENUIDLIST_NULL(UserExpPrefix + "013", "menuIdList为空"),
    PERMISSIONIDLIST_NULL(UserExpPrefix + "014", "permissionIdList为空"),
    NAME_NULL(UserExpPrefix + "015", "name为空"),
    LOCATIONID_NULL(UserExpPrefix + "016", "locationId为空"),
    LOCATIONUPDATEREQ_NULL(UserExpPrefix + "017", "LocationUpdateReq为空"),

    /** Product模块异常 */
    PRODUCT_NAME_NULL(ProdExpPrefix + "000", "产品名称为空"),
    PRODUCT_MARKETPRICE_NULL(ProdExpPrefix + "001", "产品市场价为空"),
    PRODUCT_SHOPPRICE_NULL(ProdExpPrefix + "002", "产品本店价为空"),
    PRODUCT_STOCK_ZERO(ProdExpPrefix + "003", "产品库存为0"),
    PRODUCT_WEIGHT_NULL(ProdExpPrefix + "004", "产品重量为空"),
    PRODUCT_TOPCATEENTITY_NULL(ProdExpPrefix + "005", "产品一级分类为空"),
    PRODUCT_SUBCATEGENTITY_NULL(ProdExpPrefix + "006", "产品二级分类为空"),
    PRODUCT_BRANDENTITY_NULL(ProdExpPrefix + "007", "产品品牌为空"),
    PRODUCT_COMPANYENTITY_NULL(ProdExpPrefix + "008", "产品所属企业为空"),
    PRODUCT_CREATE_FAIL(ProdExpPrefix + "009", "产品新增失败"),
    PRODUCT_UPDATE_FAIL(ProdExpPrefix + "010", "产品更新失败"),
    PRODUCT_SELECT_FAIL(ProdExpPrefix + "011", "没有符合条件的查询结果，产品查询失败"),
    PRODUCT_NO_EXISTENT(ProdExpPrefix + "025", "产品不存在，或者已下架"),
    /** Category异常 前缀统一使用 ProdExpPrefix */
    CATEGORY_NAME_NULL(ProdExpPrefix + "012","类别名称为空"),
    CATEGORY_CREATE_FAIL(ProdExpPrefix + "013","类别新增失败"),
    CATEGORY_DELETE_FAIL(ProdExpPrefix + "014","类别删除失败"),
    CATEGORY_UPDATE_FAIL(ProdExpPrefix + "015","类别更新失败"),
    CATEGORY_SELECT_FAIL(ProdExpPrefix + "016","没有符合条件的查询结果，类别查询失败"),
    CATEGORY_HASUSED(ProdExpPrefix +"017","当前类别已经被使用"),
    /** Brand异常 前缀统一使用 ProdExpPrefix */
    BRADN_NAME_NULL(ProdExpPrefix + "018","品牌名称为空"),
    BRADN_LOGO_NULL(ProdExpPrefix + "019","品牌LOGO为空"),
    BRADN_COMMPANY_NULL(ProdExpPrefix + "020","品牌所属企业为空"),
    BRADN_CREATE_FAIL(ProdExpPrefix + "021","品牌查询失败"),
    BRADN_UPDATE_FAIL(ProdExpPrefix + "022","品牌更新失败"),
    BRADN_DELETE_FAIL(ProdExpPrefix + "023","品牌删除失败"),
    BRADN_SELETE_FAIL(ProdExpPrefix + "024","没有符合条件的查询结果，品牌查询失败"),


    /** Order模块异常 */
    STOCK_LOW(OrderExpPrefix + "000", "库存不足"),
    PROCESSOR_NULL(OrderExpPrefix + "001", "受理请求不存在"),
    COMPONENT_NULL(OrderExpPrefix + "002", "业务组件列表为空"),
    PROCESSREQ_ENUM_NULL(OrderExpPrefix + "003", "受理请求的枚举为空"),
    PROCESSREQ_NULL(OrderExpPrefix + "004", "受理请求为空"),
    PROCESSREQ_ORDERID_NULL(OrderExpPrefix + "005", "受理请求中的orderId为空"),
    PROCESSREQ_USERID_NULL(OrderExpPrefix + "006", "受理请求中的userId为空"),
    AllowStateList_NULL(OrderExpPrefix + "007", "幂等性检查所需的allowStateList为空"),
    ORDER_INSERT_REQ_NULL(OrderExpPrefix + "008", "创建订单的请求参数为空"),
    USERID_NULL(OrderExpPrefix + "009", "UserId为空"),
    PAYMODE_NULL(OrderExpPrefix + "010", "支付方式不能为空，且必须符合枚举规范"),
    LOCATION_NULL(OrderExpPrefix + "011", "收货地址为空"),
    PRODUCTIDCOUNT_NULL(OrderExpPrefix + "012", "prodIdCountMap为空"),
    SELLER_DIFFERENT(OrderExpPrefix + "013", "产品的卖家必须相同"),
    TARGETSTATE_NULL(OrderExpPrefix + "014", "更新订单状态时，订单目标状态不能为空"),
    PROCESSCONTEXT_NULL(OrderExpPrefix + "015", "订单受理上下文为空"),
    PAYMODECODE_ERROR(OrderExpPrefix + "016", "支付方式code不存在"),
    ORDERQUERYREQ_NULL(OrderExpPrefix + "017", "orderQueryReq为空"),
    PRODUCTIDCOUNT_ERROR(OrderExpPrefix + "018", "产品的数量必须大于0"),
    PRODUCT_ID_NO_EXISTENT(OrderExpPrefix + "019", "产品ID不存在"),
    ORDER_NULL(OrderExpPrefix + "020", "查无此单"),
    EXPRESS_NO_NULL(OrderExpPrefix + "021", "物流单号不能为空"),
    ORDER_STATE_NULL(OrderExpPrefix + "022", "该订单的状态字段为空"),


    /** Analysis模块异常 */
    XXXX_NULL(AnlsExpPrefix + "000", "XXXX异常");

    private String code;
    private String message;

    private ExpCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    ExpCodeEnum(){}

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
