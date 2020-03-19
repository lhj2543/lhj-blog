package com.lhj.system.controller;

import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysMenu;
import com.lhj.model.system.SysRoleMenu;
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
* 角色菜单授权管理 controller
* author:liuhaijiang
*/
@RestController
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController {

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
    //@RequiresRoles({"base","admin"}) //如果subject中有这些角色菜单授权才可以访问方法。如果没有这个权限则会抛出异常AuthorizationException。
    //属于T1并且属于T2角色菜单授权可以访问
    //@RequiresRoles(value={"T1","T2"})
    //属于T1或者T2角色菜单授权可以访问
    //@RequiresRoles(value={"base","admin"},logical = Logical.OR)
    public SysMenu query(SysMenu param){

        logger.info("查询角色菜单授权列表开始");
        SysMenu result = param;
        try {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setParentId("-1");
            sysMenu.setMenuName("manage");
            sysMenu.setOrderby("sort_key");
            List<SysMenu> rootMenus = dataBaseService.selectList("findSysMenu", sysMenu);

            sysMenu.setParentId("");
            sysMenu.setMenuName("");
            sysMenu.setCustomWhere(" and parent_id <> '-1' and category like '%manage%' ");
            List<SysMenu> rows = dataBaseService.selectList("findSysMenu", sysMenu);

            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(param.getRoleId());
            List<SysRoleMenu> rms = dataBaseService.selectList("findSysRoleMenu", rm);

            for (SysMenu m:rootMenus){
                m.setExpand(param.isExpand());
                m.setRoleId(param.getRoleId());
                this.recurrenceMenu(m,rows,param,rms);
                //System.err.println(jsonSupport.toJson(m));
            }

            result.setRows(rootMenus);
            result.setSuccess(true);
            result.setMessage("查询数据成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询角色菜单授权列表异常");
            logger.error("查询角色菜单授权列表异常",e);
            e.printStackTrace();
        }
        logger.info("查询角色菜单授权列表结束");
        return  result;
    }

    /*递归角色菜单授权*/
    public void recurrenceMenu(SysMenu menu,List<SysMenu> menus,SysMenu params,List<SysRoleMenu> rms){

        for(SysMenu m:menus){

            if(StringUtils.equals(m.getParentId(),menu.getSid())){
                m.setExpand(params.isExpand());
                //判断该角色是否有该菜单
                for (SysRoleMenu rm:rms){
                    if(StringUtils.equals(rm.getMenuId(),m.getSid()) && !StringUtils.equals(m.getType(),"3")){
                        m.setChecked(true);
                        break;
                    }
                }
                m.setRoleId(params.getRoleId());
                menu.getChildren().add(m);
                this.recurrenceMenu(m,menus,params,rms);
            }

        }

    }


    @PostMapping(value = "/save")
    public SysMenu save(@RequestBody SysMenu param){

        logger.info("新增/修改角色菜单授权开始 ");
        SysMenu result = param;
        try {

            SysRoleMenu rm1 = new SysRoleMenu();
            rm1.setRoleId(param.getRoleId());
            dataBaseService.delete("deleteSysRoleMenuByRoleId",rm1);

            String userCd = sessionSupport.getUserCd();
            //Date systemDate = DateSupport.getSystemDate();

            for (SysMenu m:param.getRows()){
                SysRoleMenu rm = new SysRoleMenu();
                rm.setUpdateBy(userCd);
                rm.setUpdateDate(DateSupport.getSystemDate());
                rm.setCreatedBy(userCd);
                rm.setCreatedDate(DateSupport.getSystemDate());
                rm.setRoleId(param.getRoleId());
                rm.setMenuId(m.getSid());
                rm.setStatus("1");
                dataBaseService.insert("addSysRoleMenu",rm);
            }

            result.setSuccess(true);
            result.setMessage("保存成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("保存异常");
            logger.error("保存异常",e);
            e.printStackTrace();
        }

        logger.info("新增/修改角色菜单授权结束");
        return  result;
    }


}
