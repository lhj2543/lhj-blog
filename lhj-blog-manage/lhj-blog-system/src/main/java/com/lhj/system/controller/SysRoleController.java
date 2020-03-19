package com.lhj.system.controller;

import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysAccount;
import com.lhj.model.system.SysRole;
import com.lhj.mybatis.service.DataBaseService;
import com.lhj.system.support.Md5Supper;
import com.lhj.system.support.SessionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* 角色管理 controller
* author:liuhaijiang
*/
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    /*日志*/
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    JsonSupport jsonSupport;

    @Autowired
    SessionSupport sessionSupport;

    //@CrossOrigin /*跨域请求支持*/
    @RequestMapping(value = "/query")
    //@RequiresRoles({"base","admin"}) //如果subject中有这些角色才可以访问方法。如果没有这个权限则会抛出异常AuthorizationException。
    //属于T1并且属于T2角色可以访问
    //@RequiresRoles(value={"T1","T2"})
    //属于T1或者T2角色可以访问
    //@RequiresRoles(value={"base","admin"},logical = Logical.OR)
    public SysRole query(SysRole param){

        logger.info("查询角色列表开始");
        SysRole result = param;
        try {
            SysRole SysRole = dataBaseService.selectOne("countSysRole", param);
            result.setTotal(SysRole.getTotal());

            if(StringUtils.isBlank(param.getOrderby())){
                param.setOrderby("update_date desc");
            }

            List<SysRole> rows = dataBaseService.selectListByPage("findSysRole", param);

            result.setRows(rows);
            result.setSuccess(true);
            result.setMessage("查询数据成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询角色列表异常");
            logger.error("查询角色列表异常",e);
            e.printStackTrace();
        }
        logger.info("查询角色列表结束");
        return  result;
    }

    @RequestMapping(value = "/detail")
    public SysRole detail(SysRole param){

        logger.info("查询角色详情开始");
        SysRole result = new SysRole();
        try {

            if(StringUtils.isNotBlank(param.getSid())){
                SysRole SysRole = dataBaseService.selectOne("findSysRole", param);
                result = SysRole==null?result:SysRole;
            }else{
                //新增时初始化数据
                result.setStatus("1");
            }
            result.setSuccess(true);
            result.setMessage("查询角色详情成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询角色详情异常");
            logger.error("查询角色详情异常",e);
            e.printStackTrace();
        }

        logger.info("查询角色详情结束");

        return  result;
    }

    @PostMapping(value = "/deletes")
    public SysRole delete(@RequestBody List<SysRole> param){

        logger.info("删除角色开始");
        logger.info(jsonSupport.toJson(param));
        SysRole result = new SysRole();
        try {
            for(SysRole u:param){
                dataBaseService.delete("deleteSysRole", u);
            }

            result.setSuccess(true);
            result.setMessage("删除角色成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("删除角色异常");
            logger.error("删除角色异常",e);
            e.printStackTrace();
        }

        logger.info("删除角色结束");
        return  result;
    }


    @PostMapping(value = "/modify")
    public SysRole modify(@RequestBody SysRole param){
        String roleCode = param.getRoleCode();

        logger.info("新增/修改角色开始 roleCode="+roleCode);
        SysRole result = param;
        try {
            String userCd = sessionSupport.getUserCd();
            Date systemDate = DateSupport.getSystemDate();

            param.setUpdateBy(userCd);
            param.setUpdateDate(systemDate);

            if(StringUtils.isBlank(param.getSid())){

                SysRole acc = this.roleCodeIsExist(roleCode);
                if(acc!=null && StringUtils.isNotBlank(acc.getSid())){
                    result.setSuccess(false);
                    result.setMessage(roleCode+"该角色名已存在！");
                    return  result;
                }

                param.setCreatedBy(userCd);
                param.setCreatedDate(systemDate);
                dataBaseService.insert("addSysRole",param);

            }else{
                dataBaseService.update("updateSysRole",param);
            }

            result.setSuccess(true);
            result.setMessage("保存成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("保存异常");
            logger.error("保存异常",e);
            e.printStackTrace();
        }

        logger.info("新增/修改角色结束");
        return  result;
    }

    @RequestMapping(value = "/roleCodeIsExist")
    public SysRole roleCodeIsExist(String userCd){

        logger.info("验证角色CD是否已存在开始");
        SysRole result = new SysRole();
        try {
            result.setRoleCode(userCd);
            SysRole sysAccount = dataBaseService.selectOne("findSysRole", result);
            result = sysAccount==null?result:sysAccount;
            result.setSuccess(true);
            result.setMessage("验证角色CD是否已存在成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("验证角色CD是否已存在异常");
            logger.error("验证角色CD是否已存在异常",e);
            e.printStackTrace();
        }

        logger.info("验证角色CD是否已存在结束");
        return  result;
    }

}
