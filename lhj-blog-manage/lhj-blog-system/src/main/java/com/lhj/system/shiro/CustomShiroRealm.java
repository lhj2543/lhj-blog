package com.lhj.system.shiro;

import com.lhj.model.system.SysAccount;
import com.lhj.mybatis.service.DataBaseService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomShiroRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DataBaseService dataBaseService;

    /*
    *为当前登录成功的用户授予权限和分配角色
    * 授权(验证权限时候调用)
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.err.println("doGetAuthorizationInfo 为当前登录成功的用户授予权限和分配角色");
        return null;
    }

    /*
    *用来验证当前登录的用户，获取认证信息。
    * 认证(登陆时候调用)
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("shiro doGetAuthenticationInfo 身份验证开始--------------->");

        String userCd = (String) authenticationToken.getPrincipal();
        SysAccount account = new SysAccount();
        account.setUserCd(userCd);
        account.setLicence("1");
        //1. 根据accessToken，查询用户信息
        account = dataBaseService.selectOne("findSysAccount",account);
        if (Objects.isNull(account)) {
            return null;
        }

        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, account.getPassword(), this.getName());

        logger.info("shiro doGetAuthenticationInfo 身份验证结束--------------->");
        return info;
    }

    /**
     * 认证(登陆时候调用)
     *//*
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.err.println("doGetAuthenticationInfo");
        *//*String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = shiroService.findByToken(accessToken);
        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        User user = shiroService.findByUserId(tokenEntity.getUserId());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }回. 通常
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("", accessToken, this.getName()*//*);

        return null;
    }*/

   /* *//**
     * 授权(验证权限时候调用
     *//*
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.err.println("doGetAuthorizationInfo");
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        //User user = (User) principals.getPrimaryPrincipal();
        //Integer userId = user.getUserId();
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        *//*for (Role role : user.getRoles()) {
            //2.1添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                //2.1.1添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }*//*
        return null;
    }

    */



}