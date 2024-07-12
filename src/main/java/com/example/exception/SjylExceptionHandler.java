package com.example.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;
/*
* 全局异常处理
*
 */
@ControllerAdvice
public class SjylExceptionHandler {

    /*token失效*/
    @ExceptionHandler({ExpiredJwtException.class, MalformedJwtException.class, MissingRequestHeaderException.class})
    @ResponseBody
    public Map<Object, Object> ExpiredJwtException() {
        Map<Object, Object> result = new LinkedHashMap();
        result.put("code", 204);
        result.put("msg", "token失效,请重新登录");
        return result;
    }
    /*缺少参数*/
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Map<Object, Object> IllegalArgumentException() {
        Map<Object, Object> result = new LinkedHashMap();
        result.put("code", 205);
        result.put("msg", "缺少参数");
        return result;
    }


}
