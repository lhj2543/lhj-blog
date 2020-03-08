package com.lhj.system.shiro;

import com.lhj.common.model.Pojo;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/*
shiro 全局异常铺货
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    /*
    没有权限异常
    * */
    @ExceptionHandler(UnauthorizedException.class)
    public Pojo UnauthorizedException(HttpServletRequest req, Exception e){

        System.err.println("进入全局UnauthorizedException 异常拦截 Exception="+e.getMessage());

        Pojo result = new Pojo();
        Set<Exception> es= new HashSet<Exception>();
        result.setErrors(es);

        result.setMessage("您没有权限，请联系管理员");

        result.setResponseCode("402");

        return  result;
    }

}
