package com.lhj.system.controller;

import com.lhj.common.model.Pojo;
import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysAccount;
import com.lhj.mybatis.service.DataBaseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ShiroController {

    @Autowired
    DataBaseService dataBaseService;

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/unAuth")
    public Pojo unAuth(){
        Pojo result = new Pojo();

        result.setSuccess(false);
        result.setMessage("抱歉，您没有权限，请联系管理员!");

        return result;
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     */
    @RequestMapping("/unAuthorized")
    public Pojo unauthorized() {
        Pojo result =new Pojo();
        result.setMessage("用户无权限！");
        return result;
    }

    //@RequestMapping(value="/login",method = RequestMethod.POST)
    @PostMapping("/login")
    public SysAccount login(SysAccount param){

        logger.info("开始登陆，用户名="+param.getUserCd());
        SysAccount result = new SysAccount();
        try {
            result.setUserCd(param.getUserCd());
            result.setLicence("1");
            SysAccount accout = dataBaseService.selectOne("findSysAccount",result);

            if(accout==null){
                result.setMessage("该账户不存在或账户已禁用！");
            }else{
                result = accout;
                Date startDate = result.getStartDate();
                Date endDate = result.getEndDate();
                if(!(startDate !=null && endDate!=null && DateSupport.isEffectiveDate(new Date(),startDate,endDate))){
                    result.setMessage("账户已过期！");
                }

                String userCd = param.getUserCd();
                String password = param.getPassword();
                if(!StringUtils.equals(result.getPassword(),password)){
                    result.setMessage("账户密码错误！");
                }

                // 获取Subject实例对象，用户实例
                Subject subject = SecurityUtils.getSubject();

                // 将用户名和密码封装到UsernamePasswordToken
                UsernamePasswordToken token = new UsernamePasswordToken(userCd, password);

                // 传到自定义 CustomShiroRealm 类中的方法进行认证
                subject.login(token);

                // 构建缓存用户信息返回给前端
                SysAccount shiroRespAccount = (SysAccount) subject.getPrincipals().getPrimaryPrincipal();

                //缓存
                //cacheUser = CacheUser.builder().token(subject.getSession().getId().toString()).build();

                result.setSuccess(true);
                result.setMessage("登录成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("登陆异常！");
            logger.error("登陆异常！");
        }

        logger.info("结束登陆，用户名="+param.getUserCd());

        return result;
    }

    /**
     * 注销
     */
    @RequestMapping("/logout")
    public Pojo logout() {

        Pojo result =new Pojo();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();

            result.setSuccess(true);
            result.setMessage("注销成功");
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
