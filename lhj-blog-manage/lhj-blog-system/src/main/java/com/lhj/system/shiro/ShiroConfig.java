package com.lhj.system.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
*SpringBoot shrio 配置
* author:liuhaijiang
*/
@Configuration
public class ShiroConfig {

    /**
     * 请求拦截
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filtersMap = new HashMap<String, Filter>();
        /*自定义过滤器*/
        CustomPassThruAuthenticationFilter authFilter = new CustomPassThruAuthenticationFilter();
        filtersMap.put("authc", authFilter);
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 拦截器.
        /*
         * 注意过滤器配置顺序 不能颠倒
         * anon. 配置不会被拦截的请求 顺序判断
         * authc. 配置拦截的请求
         * 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
         * 所有非法请求被重定向到/unauth，该请求返回一个json数据
         * shiroFilterFactoryBean.setLoginUrl("/unAuth");
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/login", "anon");//不拦截
        filterChainDefinitionMap.put("/site/**", "anon");//不拦截
        filterChainDefinitionMap.put("/images/**", "anon");//不拦截
        filterChainDefinitionMap.put("/actuator/info", "anon");//不拦截
        filterChainDefinitionMap.put("/hystrix.stream", "anon");//不拦截

        filterChainDefinitionMap.put("/**", "authc");//拦截所有

        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了, 位置放在 anon、authc下面
        filterChainDefinitionMap.put("/logout", "logout");

        shiroFilterFactoryBean.setLoginUrl("/unAuth");

        // 未授权界面, 对应LoginController中 unAuthorized 请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorized");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    //解决报错UnavailableSecurityManagerException问题
    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * @Title: securityManager
     * @Description: SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.myShiroRealm());
        securityManager.setSessionManager(this.sessionManager());
        return securityManager;
    }

    /**
     * 自定义认证
     * @Title: myShiroRealm
     * @Description: ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理
     * @return MyShiroRealm
     */
    @Bean
    public CustomShiroRealm myShiroRealm() {
        CustomShiroRealm myShiroRealm = new CustomShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 密码凭证匹配器，作为自定义认证的基础 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义sessionManager，用户的唯一标识，即Token或Authorization的认证
     */
    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager mySessionManager = new CustomSessionManager();
        // 设置session过期时间 默认30分钟
        /*long timeOut = 1000*60;
        mySessionManager.setGlobalSessionTimeout(timeOut);*/

        mySessionManager.setSessionDAO(this.sessionDAO());

        return mySessionManager;
    }

    @Bean("SessionDao")
    public SessionDAO sessionDAO(){
        MemorySessionDAO sessionDAO = new MemorySessionDAO();
        return  sessionDAO;
    }

    /*
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @RequiresAuthentication
        验证用户是否登录，等同于方法subject.isAuthenticated() 结果为true时。
        @RequiresUser
        验证用户是否被记忆，user有两种含义：
        一种是成功登录的（subject.isAuthenticated() 结果为true）；
        另外一种是被记忆的（subject.isRemembered()结果为true）。
        @RequiresGuest
        验证是否是一个guest的请求，与@RequiresUser完全相反。
         换言之，RequiresUser  == !RequiresGuest。
        此时subject.getPrincipal() 结果为null.
        @RequiresRoles
        例如：@RequiresRoles("aRoleName");
          void someMethod();
        如果subject中有aRoleName角色才可以访问方法someMethod。如果没有这个权限则会抛出异常AuthorizationException。
        @RequiresPermissions
        例如： @RequiresPermissions({"file:read", "write:aFile.txt"} )
          void someMethod();
        要求subject中必须同时含有file:read和write:aFile.txt的权限才能执行方法someMethod()。否则抛出异常AuthorizationException。
      */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleCookie cookie() {
        // cookie的name,对应的默认是 JSESSIONID
        SimpleCookie cookie = new SimpleCookie("SHARE_JSESSIONID");
        cookie.setHttpOnly(true);
        //  path为 / 用于多个系统共享 JSESSIONID
        cookie.setPath("/");
        return cookie;
    }

}
