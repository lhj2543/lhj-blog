package com.lhj.system.controller;

import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysMenu;
import com.lhj.mybatis.service.DataBaseService;
import com.lhj.system.support.SessionSupport;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/*
* 菜单管理 controller
* author:liuhaijiang
*/
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

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
    //@RequiresRoles({"base","admin"}) //如果subject中有这些菜单才可以访问方法。如果没有这个权限则会抛出异常AuthorizationException。
    //属于T1并且属于T2菜单可以访问
    //@RequiresRoles(value={"T1","T2"})
    //属于T1或者T2菜单可以访问
    //@RequiresRoles(value={"base","admin"},logical = Logical.OR)
    public SysMenu query(SysMenu param){

        logger.info("查询菜单列表开始");
        SysMenu result = param;
        try {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setParentId("-1");
            sysMenu.setOrderby("sort_key");
            List<SysMenu> rootMenus = dataBaseService.selectList("findSysMenu", sysMenu);

            sysMenu.setParentId("");
            sysMenu.setCustomWhere(" and parent_id <> '-1' ");
            List<SysMenu> rows = dataBaseService.selectList("findSysMenu", sysMenu);

            for (SysMenu m:rootMenus){
                m.setExpand(param.isExpand());
                this.recurrenceMenu(m,rows,param);
                //System.err.println(jsonSupport.toJson(m));
            }

            result.setRows(rootMenus);
            result.setSuccess(true);
            result.setMessage("查询数据成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询菜单列表异常");
            logger.error("查询菜单列表异常",e);
            e.printStackTrace();
        }
        logger.info("查询菜单列表结束");
        return  result;
    }

    /*递归菜单*/
    public void recurrenceMenu(SysMenu menu,List<SysMenu> menus,SysMenu params){

        for(SysMenu m:menus){

            if(StringUtils.equals(m.getParentId(),menu.getSid())){
                m.setExpand(params.isExpand());
                menu.getChildren().add(m);
                this.recurrenceMenu(m,menus,params);
            }

        }

    }

    @RequestMapping(value = "/detail")
    public SysMenu detail(SysMenu param){

        logger.info("查询菜单详情开始");
        SysMenu result = new SysMenu();
        try {

            if(StringUtils.isNotBlank(param.getSid())){
                SysMenu SysMenu = dataBaseService.selectOne("findSysMenu", param);
                result = SysMenu==null?result:SysMenu;
            }else{
                //新增时初始化数据
                result.setStatus("1");
            }
            result.setSuccess(true);
            result.setMessage("查询菜单详情成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询菜单详情异常");
            logger.error("查询菜单详情异常",e);
            e.printStackTrace();
        }

        logger.info("查询菜单详情结束");

        return  result;
    }

    @PostMapping(value = "/deletes")
    public SysMenu delete(@RequestBody SysMenu param){

        logger.info("删除菜单开始");
        logger.info(jsonSupport.toJson(param));
        SysMenu result = new SysMenu();
        try {

            if(param.getChildren().size()>0){
                this.delChildrenMenu(param.getChildren());
            }

            dataBaseService.delete("deleteSysMenu", param);

            result.setSuccess(true);
            result.setMessage("删除菜单成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("删除菜单异常");
            logger.error("删除菜单异常",e);
            e.printStackTrace();
        }

        logger.info("删除菜单结束");
        return  result;
    }

    /*递归删除菜单*/
    public void delChildrenMenu(List<SysMenu> menus){

        for(SysMenu m:menus){
            dataBaseService.delete("deleteSysMenu", m);
            this.delChildrenMenu(m.getChildren());
        }

    }


    @PostMapping(value = "/save")
    public SysMenu save(@RequestBody SysMenu param){

        logger.info("新增/修改菜单开始 ");
        SysMenu result = param;
        try {

            String userCd = sessionSupport.getUserCd();
            Date systemDate = DateSupport.getSystemDate();

            param.setUpdateBy(userCd);
            param.setUpdateDate(systemDate);

            if(StringUtils.isBlank(param.getSid())){

                param.setCreatedBy(userCd);
                param.setCreatedDate(systemDate);
                dataBaseService.insert("addSysMenu",param);

            }else{
                dataBaseService.update("updateSysMenu",param);
            }

            result.setSuccess(true);
            result.setMessage("保存成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("保存异常");
            logger.error("保存异常",e);
            e.printStackTrace();
        }

        logger.info("新增/修改菜单结束");
        return  result;
    }

    @RequestMapping(value = "/roleCodeIsExist")
    public SysMenu roleCodeIsExist(String userCd){

        logger.info("验证菜单CD是否已存在开始");
        SysMenu result = new SysMenu();
        /*try {
            result.setRoleCode(userCd);
            SysMenu sysAccount = dataBaseService.selectOne("findSysMenu", result);
            result = sysAccount==null?result:sysAccount;
            result.setSuccess(true);
            result.setMessage("验证菜单CD是否已存在成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("验证菜单CD是否已存在异常");
            logger.error("验证菜单CD是否已存在异常",e);
            e.printStackTrace();
        }*/

        logger.info("验证菜单CD是否已存在结束");
        return  result;
    }

}
