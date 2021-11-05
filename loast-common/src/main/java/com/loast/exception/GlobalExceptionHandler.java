package com.loast.exception;


import cn.dev33.satoken.exception.NotLoginException;
import com.loast.conn.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.loast.result.R;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


/**
 * 全剧异常处理
 *
 * @author arman
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public R sqlException(SQLException e) {
        // 打印堆栈，以供调试
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(BusinessException.class)
    public R businessException(BusinessException e) {
        // 打印堆栈，以供调试
        // e.printStackTrace();
        log.error("报错信息 exception:{}", Arrays.toString(e.getStackTrace()));
        if (null != e.getResultCodeEnum()) {
            return R.errorMsg(e.getResultCodeEnum(), e.getMessage());
        }
        return R.error(e.getMessage());
    }

    /**
     *  入参校验错误
     *  MethodArgumentNotValidException 就是 当对用@Valid注释的参数的验证失败时抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleException(MethodArgumentNotValidException e, HttpServletRequest request) {
        // 拿到 message 里面的消息
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        FieldError error = fieldErrors.get(0);
        String msg = error.getDefaultMessage();
        log.error("msg {}", error);
        return R.error(msg);
    }

    @ExceptionHandler(NotLoginException.class)
    public R handlerNotLoginException(NotLoginException nle) {
        log.error("报错信息 exception:{}", nle.getMessage());
        // 判断场景值，定制化异常信息
        String message;
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }
        // 返回给前端
        return R.errorMsg(ResultCodeEnum.LOGIN_ERROR, message);
    }

    @ExceptionHandler(Exception.class)
    public R businessException(Exception e, HttpServletRequest request) {
        // 打印堆栈，以供调试
        // e.printStackTrace();
        log.error("服务内部错误，路径 url:{}, exception:{}", request.getRequestURI(), e);
        return R.error();
    }

}