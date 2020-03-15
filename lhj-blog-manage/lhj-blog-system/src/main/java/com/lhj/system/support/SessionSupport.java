package com.lhj.system.support;

import com.lhj.model.system.SysAccount;
import com.lhj.model.system.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/*
* shiro session 支撑类
* author:liuhaijiang
*/
@Component
public class SessionSupport {

    @Autowired(required=false)
    SessionDAO sessionDAO;

    /**
     * 获得当前用户
     */
    public SysUser getSysUser(){
        SysUser result = new SysUser();
    	try {
            Subject subject = SecurityUtils.getSubject();
            result = (SysUser)subject.getPrincipal();
		} catch (Exception e) {
    	    e.printStackTrace();
        }
    	return result;
    }

    /**
     * 获得当前用户cd
     */
    public String getUserCd(){
        String result = "";
        try {
            result = this.getSysUser().getUserCd();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获得当前用户姓名
     */
    public String getUserName(){
        String result = "";
        try {
            result = this.getSysUser().getUserName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断当前在线用户是否为系统超级管理员（内置管理员）
     * @return
     */
    public boolean isAdministrator(){
        if("administrator".equals(this.getSysUser().getUserCd())){
            return true;
        }
        return false;
    }

    /**
     * 获得当前所有在线用户
     */
    public List<SysUser> getActiveSysAccounts(){

        List<SysUser> result = new ArrayList<SysUser>();
        try {
            //apache shiro获取所有在线用户
            Collection<Session> sessions = sessionDAO.getActiveSessions();
            for(Session session:sessions) {
                //获得session中已经登录用户的名字
                SimplePrincipalCollection principle = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (principle != null) {
                    SysUser account = (SysUser) principle.getPrimaryPrincipal();
                    result.add(account);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获得请求对象
     * @return
     */
    public HttpServletRequest getRequest() {
    	return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    /**
     * 获得请求 IP 地址
     * @author lhj <br>
     * @return
     */
     public String getUserIp() {
    	 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         String ip = null;
         Enumeration<String> enu = request.getHeaderNames();
         while (enu.hasMoreElements()) {
             String name = enu.nextElement();
             if (name.equalsIgnoreCase("X-Forwarded-For") || name.equalsIgnoreCase("X-Real-IP")
                     || name.equalsIgnoreCase("Proxy-Client-IP")
                     || name.equalsIgnoreCase("WL-Proxy-Client-IP")) {
                 ip = request.getHeader(name);
             }
             if (ip != null && ip.length() >= 0 && !"unknown".equalsIgnoreCase(ip)) {
                 break;
             }
         }
         if (ip == null || ip.length() == 0) {
             ip = request.getRemoteAddr();
         }
         if (StringUtils.isNotEmpty(ip)) {
             ip = ip.split(",")[0];
         }
         return ip;
     }
    
}

