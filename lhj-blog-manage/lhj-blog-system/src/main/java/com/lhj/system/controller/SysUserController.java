package com.lhj.system.controller;

import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysAccount;
import com.lhj.model.system.SysRole;
import com.lhj.model.system.SysUser;
import com.lhj.model.system.SysUserRole;
import com.lhj.mybatis.service.DataBaseService;
import com.lhj.system.service.SysUserService;
import com.lhj.system.support.Md5Supper;
import com.lhj.system.support.SessionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/*
* 用户管理 controller
* author:liuhaijiang
*/
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    /*日志*/
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    JsonSupport jsonSupport;

    @Autowired
    SessionSupport sessionSupport;

    @Autowired
    SysUserService sysUserService;

    //@CrossOrigin /*跨域请求支持*/
    @RequestMapping(value = "/query")
    //@RequiresRoles({"base","admin"}) //如果subject中有这些角色才可以访问方法。如果没有这个权限则会抛出异常AuthorizationException。
    //属于T1并且属于T2角色可以访问
    //@RequiresRoles(value={"T1","T2"})
    //属于T1或者T2角色可以访问
    @RequiresRoles(value={"base","admin"},logical = Logical.OR)
    public SysUser query(SysUser param){

        logger.info("查询用户列表开始");
        SysUser result = param;
        try {
            SysUser sysUser = dataBaseService.selectOne("countSysUser", param);
            result.setTotal(sysUser.getTotal());
            if(StringUtils.isBlank(param.getOrderby())){
                param.setOrderby("update_date desc");
            }
            List<SysUser> rows = dataBaseService.selectListByPage("findSysUser", param);

            result.setRows(rows);
            result.setSuccess(true);
            result.setMessage("查询数据成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询用户列表异常");
            logger.error("查询用户列表异常",e);
            e.printStackTrace();
        }
        logger.info("查询用户列表结束");
        return  result;
    }

    @RequestMapping(value = "/detail")
    public SysUser detail(SysUser param){

        logger.info("查询用户详情开始");
        SysUser result = new SysUser();
        try {

            if(StringUtils.isNotBlank(param.getSid())){
                SysUser sysUser = dataBaseService.selectOne("findSysUser_Detail", param);
                result = sysUser==null?result:sysUser;
                SysAccount sysAccount = result.getSysAccount();
                if(sysAccount == null){
                    result.setSysAccount(new SysAccount());
                }
            }else{
                //新增时初始化数据
                SysAccount sysAccount = new SysAccount();
                sysAccount.setLicence("1");
                sysAccount.setType("1");
                sysAccount.setStartDate(new Date());
                sysAccount.setEndDate(DateSupport.formatDateTime("2999-12-31","yyyy-MM-dd"));
                result.setSysAccount(sysAccount);
            }
            result.setSuccess(true);
            result.setMessage("查询用户详情成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询用户详情异常");
            logger.error("查询用户详情异常",e);
            e.printStackTrace();
        }

        logger.info("查询用户详情结束");

        return  result;
    }

    @PostMapping(value = "/delRole")
    public SysUser delRole(@RequestBody SysUserRole param){

        logger.info("删除用户角色开始");

        SysUser result = new SysUser();
        try {
            dataBaseService.delete("deleteSysUserRole", param);
            result.setSuccess(true);
            result.setMessage("删除用户角色成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("删除用户角色异常");
            logger.error("删除用户角色异常",e);
            e.printStackTrace();
        }

        logger.info("删除用户角色结束");
        return  result;
    }

    @PostMapping(value = "/deletes")
    public SysUser delete(@RequestBody List<SysUser> param){

        logger.info("删除用户开始");
        logger.info(jsonSupport.toJson(param));
        SysUser result = new SysUser();
        try {
            for(SysUser u:param){
                dataBaseService.delete("deleteSysUser", u);

                SysAccount sysAccount = new SysAccount();
                sysAccount.setUserCd(u.getUserCd());
                dataBaseService.delete("deleteSysAccountByUserCd", sysAccount);
            }

            result.setSuccess(true);
            result.setMessage("删除用户成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("删除用户异常");
            logger.error("删除用户异常",e);
            e.printStackTrace();
        }

        logger.info("删除用户结束");
        return  result;
    }


    @PostMapping(value = "/modify")
    public SysUser modify(@RequestBody SysUser param){
        String userCd = param.getUserCd();

        logger.info("新增/修改用户开始 usreCd="+userCd);
        SysUser result = param;
        try {

            result = sysUserService.modify(param);

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("保存异常");
            logger.error("保存异常",e);
            e.printStackTrace();
        }

        logger.info("新增/修改用户结束");
        return  result;
    }

    @RequestMapping(value = "/userCdIsExist")
    public SysAccount userCdIsExist(String userCd){

        logger.info("验证用户CD是否已存在开始");
        SysAccount result = new SysAccount();
        try {
            result.setUserCd(userCd);
            SysAccount sysAccount = dataBaseService.selectOne("findSysAccount", result);
            result = sysAccount==null?result:sysAccount;
            result.setSuccess(true);
            result.setMessage("验证用户CD是否已存在成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("验证用户CD是否已存在异常");
            logger.error("验证用户CD是否已存在异常",e);
            e.printStackTrace();
        }

        logger.info("验证用户CD是否已存在结束");
        return  result;
    }

}
