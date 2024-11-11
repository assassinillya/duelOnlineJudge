package com.asily.config;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SaTokenException.class)
    public SaResult handlerSaTokenException(SaTokenException e) {
        String error = String.format("错误码: %d, %s, 服务器繁忙，请稍后重试...", e.getCode(), e.getMessage());
        // 默认的提示
        return SaResult.error(error);
    }
}