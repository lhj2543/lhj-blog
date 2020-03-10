package com.lhj.system.controller;

import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysAccount;
import com.lhj.model.system.SysRole;
import com.lhj.model.system.SysUser;
import com.lhj.mybatis.service.DataBaseService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    /*日志*/
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    JsonSupport jsonSupport;

    //@CrossOrigin /*跨域请求支持*/
    @RequestMapping(value = "/query")
    //@RequiresRoles({"base","admin"}) //如果subject中有这些角色才可以访问方法。如果没有这个权限则会抛出异常AuthorizationException。
    //属于T1并且属于T2角色可以访问
    //@RequiresRoles(value={"T1","T2"})
    //属于T1或者T2角色可以访问
    @RequiresRoles(value={"base","admin"},logical = Logical.OR)
    public SysUser query(SysUser param){

        logger.info("查询用户列表开始");

        try {

            SysUser sysUser = dataBaseService.selectOne("countSysUser", param);
            param.setTotal(sysUser.getTotal());

            List<SysUser> users = dataBaseService.selectListByPage("findSysUser", param);

            param.setRows(users);
            param.setSuccess(true);
            param.setMessage("查询数据成功");

        } catch(Exception e) {
            logger.error("查询用户列表异常",e);
            param.setMessage("查询用户列表异常");
            e.printStackTrace();
        }
        logger.info("查询用户列表结束");
        return  param;
    }

    @RequestMapping(value = "/queryAll")
    public List<SysUser> queryAll(SysUser param){

        logger.info("查询用户列表开始");
        List<SysUser> result = new ArrayList<SysUser>();
        try {
            result = dataBaseService.selectList("findSysUser",param);
        } catch(Exception e) {
            param.setMessage("查询用户列表异常");
            logger.error("查询用户列表异常",e);
            e.printStackTrace();
        }
        logger.info("查询用户列表结束");
        return  result;
    }

}
