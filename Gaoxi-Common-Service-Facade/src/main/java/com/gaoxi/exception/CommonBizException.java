package com.gaoxi.exception;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午10:37
 * 通用业务异常（由异常状态码区分不同的业务异常）
 */
public class CommonBizException extends RuntimeException {
    private ExpCodeEnum codeEnum;

    public CommonBizException(ExpCodeEnum codeEnum){
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }

    public ExpCodeEnum getCodeEnum() {
        return codeEnum;
    }
}
