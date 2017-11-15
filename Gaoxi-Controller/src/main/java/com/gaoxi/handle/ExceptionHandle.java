package com.gaoxi.handle;

import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.rsp.Result;
import com.gaoxi.exception.CommonBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 大闲人柴毛毛
 * @Date 2017/10/27 下午11:02
 * REST接口的通用异常处理
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 业务异常处理
     * @param exception
     * @param <T>
     * @return
     */
    @ExceptionHandler(CommonBizException.class)
    public <T> Result<T> exceptionHandler(CommonBizException exception) {
        return Result.newFailureResult(exception);
    }

    /**
     * 请求方法不正确
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        throw new CommonBizException(ExpCodeEnum.HTTP_REQ_METHOD_ERROR);
    }

    /**
     * 系统异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public <T> Result<T> sysExpHandler(Exception exception) {
        logger.error("系统异常 ",exception);
        return Result.newFailureResult();
    }

}
