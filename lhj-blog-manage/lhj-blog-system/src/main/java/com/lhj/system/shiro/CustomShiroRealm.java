package com.lhj.system.shiro;

import com.lhj.common.exception.BusinessServiceException;
import com.lhj.common.support.DateSupport;
import com.lhj.model.system.SysAccount;
import com.lhj.model.system.SysRole;
import com.lhj.mybatis.service.DataBaseService;
import com.lhj.system.support.Md5Supper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.*;

@Component
public class CustomShiroRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 是否开启登录失败次数过多，不允许登录，默认true
     */
    @Value("${app.login.isOpenFailLock:true}")
    private boolean isOpenFailLock;

    /**
     * 用户登录失败次数，默认允许登录失败10次
     */
    @Value("${app.login.failCount:10}")
    private int	loginFailCount;

    /**
     * 用户只允许一处登录，默认true
     */
    @Value("${app.login.isOneLogin:true}")
    private boolean isOneLogin;

    @Autowired
    DataBaseService dataBaseService;

    /*
    *shiro SessionDAO
     */
    @Autowired(required = false)
    SessionDAO sessionDao;

    /*
    * 授权(验证权限时候调用)
    * Subject.isPermitted() 调用，或注解@RequiresRoles,@RequiresPermissions
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("shiro doGetAuthorizationInfo 验证权限开始----------------->");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        SysAccount account = (SysAccount) principalCollection.getPrimaryPrincipal();

        // 查询用户角色，一个用户可能有多个角色
        Set<SysRole> userRoles = account.getUserRoles();

        for (SysRole role : userRoles) {
            authorizationInfo.addRole(role.getRoleCode());
            // 根据角色查询权限
            /*List<Permission> permissions = iPermissionService.getRolePermissions(role.getRoleId());
            for (Permission p : permissions) {
                authorizationInfo.addStringPermission(p.getPermission());
            }*/
        }

        logger.info("shiro doGetAuthorizationInfo 验证权限结束----------------->");

        return authorizationInfo;
    }

    /*
    *用来验证当前登录的用户，获取认证信息。
    * 认证(登陆时候调用)
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("shiro doGetAuthenticationInfo 身份验证开始--------------->");

        CustomUsernamePasswordToken token = (CustomUsernamePasswordToken) authenticationToken;

        /*获取用户输入账户*/
        String userCd = (String) authenticationToken.getPrincipal();
        /*获取用户输入密码*/
        String password = new String ((char[])authenticationToken.getCredentials());
        String md5Pwd = Md5Supper.getMd5Value(password,2,userCd);

        Map<String, Object> other = token.getOther();

        /*是否强制登录*/
        boolean isForceLogin = false;
        if(other!=null && other.get("isForceLogin")!=null && StringUtils.equals(other.get("isForceLogin").toString(),"1")){
            isForceLogin = true;
        }


        SysAccount account = new SysAccount();
        account.setUserCd(userCd);
        account.setLicence("1");

        //1. 根据accessToken，查询用户信息
        account = dataBaseService.selectOne("findSysAccount",account);


        if (Objects.isNull(account)) {
            //throw new BusinessServiceException("该账户不存在或账户已禁用！");
            throw new UnknownAccountException("账户密码错误！");
        }else{
            Date startDate = account.getStartDate();
            Date endDate = account.getEndDate();
            if(!(startDate !=null && endDate!=null && DateSupport.isEffectiveDate(new Date(),startDate,endDate))){
                throw new AuthenticationException("账户已过期！");
            }

            if(!StringUtils.equals(account.getPassword(),md5Pwd)){
                throw new AuthenticationException("账户或密码错误！");
            }

         }

        // 用户只允许一处登录 验证
        if(isOneLogin && sessionDao!=null){
            //apache shiro获取所有在线用户
            Collection<Session> sessions = sessionDao.getActiveSessions();
            for(Session session:sessions){
                //获得session中已经登录用户的名字
                SimplePrincipalCollection principle=(SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if(principle!=null){
                    SysAccount u = (SysAccount)principle.getPrimaryPrincipal();
                    if(StringUtils.equals(u.getUserCd(),userCd)){
                        logger.debug("帐号:"+userCd+"，已经在其他设备上登录");
                        if(isForceLogin){
                            // 如果是强制登录，则将其他设备上用户信息注销
                            session.setTimeout(0);
                            sessionDao.delete(session);
                        }else{
                            throw new AuthenticationException("412");
                        }
                    }
                }
            }
        }
        // 用户只允许一处登录 验证  END


        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, account.getPassword(),ByteSource.Util.bytes(userCd), this.getName());


        logger.info("shiro doGetAuthenticationInfo 身份验证结束--------------->");
        return info;
    }

}