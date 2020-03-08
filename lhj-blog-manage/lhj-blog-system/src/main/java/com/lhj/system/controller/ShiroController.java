package com.lhj.system.controller;

import com.lhj.common.exception.BusinessServiceException;
import com.lhj.common.model.Pojo;
import com.lhj.model.system.SysAccount;
import com.lhj.mybatis.service.DataBaseService;
import com.lhj.system.shiro.CustomUsernamePasswordToken;
import com.lhj.system.support.SessionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShiroController {

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    SessionSupport sessionSupport;

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/unAuth")
    public Pojo unAuth(){
        Pojo result = new Pojo();
        result.setResponseCode("401");
        result.setSuccess(false);
        result.setMessage("抱歉，您未登录，请先登录!");

        return result;
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     */
    @RequestMapping("/unAuthorized")
    public Pojo unauthorized() {
        Pojo result =new Pojo();
        result.setResponseCode("402");
        result.setMessage("抱歉，您没有权限，请联系管理员!");
        return result;
    }

    //@RequestMapping(value="/login",method = RequestMethod.POST)
    @PostMapping("/login")
    //@JsonIgnoreProperties(value = {"handler"}) //mybitis 关联查询懒加载 返回jison 异常 。作用是json序列化时忽略bean中的一些属性序列化和反序列化时抛出的异常.
    public SysAccount login(@RequestBody  SysAccount param){

        logger.info("开始登陆，用户名="+param.getUserCd());
        SysAccount result = new SysAccount();
        try {
            String userCd = param.getUserCd();
            String password = param.getPassword();

            if(StringUtils.isBlank(userCd) || StringUtils.isBlank(password) ){
                result.setMessage("账户，密码不能为空！");
                return  result;
            }

            // 获取Subject实例对象，用户实例
            Subject subject = SecurityUtils.getSubject();

            // 将用户名和密码封装到UsernamePasswordToken
            //UsernamePasswordToken token = new UsernamePasswordToken(userCd, password);
            Map<String,Object> other = new HashMap<String,Object>();
            other.put("isForceLogin",param.isForceLogin());

            CustomUsernamePasswordToken token = new CustomUsernamePasswordToken(userCd, password,other);

            // 传到自定义 CustomShiroRealm 类中的方法进行认证
            subject.login(token);

            // 构建缓存用户信息返回给前端
            result = (SysAccount) subject.getPrincipals().getPrimaryPrincipal();

            //缓存
            //cacheUser = CacheUser.builder().token(subject.getSession().getId().toString()).build();

            Session session = subject.getSession();
            String tokenId = session.getId().toString();
            //shiro 默认session有效期30分钟
            long timeout = session.getTimeout();
            //subject.isAuthenticated()//是否已登录验证
            logger.info(userCd+" token="+tokenId +" 有效期："+ (timeout/(1000*60))+"分钟");

            result.setToken(tokenId);
            result.setSuccess(true);
            result.setMessage("登录成功");
        } catch (UnknownAccountException e) {
            logger.error(e.getMessage());
            result.setMessage(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            logger.error(e.getMessage());
            result.setMessage("用户名或密码错误！");
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            result.setMessage(e.getMessage());
        }catch (BusinessServiceException e) {
            logger.error(e.getMessage());
            result.setMessage(e.getMessage());
        } catch (Exception e){
            //e.printStackTrace();
            result.setMessage("登陆异常！");
            logger.error("登陆异常！" + e.getMessage());
        }

        logger.info("结束登陆，用户名="+param.getUserCd());

        return result;
    }

    /**
     * 注销
     */
    @RequestMapping("/logout")
    public Pojo logout() {
        logger.info("用户名注销userCd="+sessionSupport.getUserCd());
        Pojo result =new Pojo();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();

            result.setSuccess(true);
            result.setMessage("注销成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("注销异常");
        }
        return result;
    }

}
