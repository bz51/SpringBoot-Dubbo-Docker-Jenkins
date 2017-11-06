package com.gaoxi.exception;

/**
 * @author 大闲人柴毛毛
 * @date 2017/11/6 下午2:11
 *
 * @description 通用系统异常
 */
public class CommonSysException extends RuntimeException  {

    private ExpCodeEnum codeEnum;

    public CommonSysException(ExpCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }
}
