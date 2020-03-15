package com.lhj.system.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

/*
    自定义拦截器
    author:liuhaijiang
 */

public class CustomPassThruAuthenticationFilter extends PassThruAuthenticationFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /*获取请求方法，若为OPTIONS请求直接返回True放行
    * 前后端分离时，前端首先会发起一个OPTIONS 请求，验证是否可跨域请求
     */

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        log.info("进入shiro CustomPassThruAuthenticationFilter onPreHandle");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            log.info("OPTIONS方法直接返回True");
            return true;
        }
        return super.onPreHandle(request, response, mappedValue);
    }

    /*
    * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
    * */
   /* @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        Subject subject = this.getSubject(request, response);
        return subject.isAuthenticated();

    }*/

    /*
    * onAccessDenied 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
    * */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResp = WebUtils.toHttp(response);
        HttpServletRequest httpReq = WebUtils.toHttp(request);

        log.info("进入shiro CustomPassThruAuthenticationFilter onAccessDenied");

        /** 系统重定向会默认把请求头清空，这里通过拦截器重新设置请求头，解决跨域问题 */
        httpResp.addHeader("Access-Control-Allow-Origin", httpReq.getHeader("Origin"));
        httpResp.addHeader("Access-Control-Allow-Headers", "*");
        httpResp.addHeader("Access-Control-Allow-Methods", "*");
        httpResp.addHeader("Access-Control-Allow-Credentials", "true");

        if (isLoginRequest(request, response)) {
            return true;
        } else {
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
}