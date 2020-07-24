package com.lhj.blog.controller.manage;

import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.*;
import com.lhj.mybatis.service.DataBaseService;
import com.lhj.system.support.SessionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/home")
public class HomeController {

    /*日志*/
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    JsonSupport jsonSupport;

    @Autowired
    SessionSupport sessionSupport;


    @PostMapping(value = "/loadMenus")
    public SysMenu loadMenus(@RequestBody SysMenu param){

        logger.info("加载后台菜单开始");

        SysMenu result = new SysMenu();

        try {

            param.setParentId("-1");
            param.setCategory("manage");
            SysMenu sysMenu = dataBaseService.selectOne("findSysMenu", param);
            if(sysMenu != null){
                result = sysMenu;

                SysUser sysUser = sessionSupport.getSysUser();
                Set<SysUserRole> userRoles = sysUser.getUserRoles();

                if(userRoles.size()<=0){//角色未空时，给基本角色
                    SysRole role = new SysRole();
                    role.setRoleCode("base");
                    role = dataBaseService.selectOne("findSysRole", role);
                    SysUserRole ur = new SysUserRole();
                    ur.setRoleCode(role.getRoleCode());
                    ur.setRoleId(role.getSid());
                    ur.setRoleName(role.getRoleName());
                    userRoles.add(ur);
                }

                param.setUserRoles(userRoles);

                param.setAdministrator(sessionSupport.isAdministrator());
                List<SysMenu> rows = dataBaseService.selectList("findSysMenuByUserRole", param);
                result.setRows(rows);
                result.setSuccess(true);
                result.setMessage("加载后台菜单成功");
            }else {
                result.setMessage("菜单数据为空");
            }


        } catch(Exception e) {
            logger.error("加载后台菜单异常",e);
            result.setMessage("加载后台菜单异常");
            e.printStackTrace();
        }

        logger.info("加载后台菜单结束");

        return  result;
    }



}
