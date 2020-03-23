package com.lhj.system.controller;

import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysItem;
import com.lhj.model.system.SysItemCategory;
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
* 字典分类管理 controller
* author:liuhaijiang
*/
@RestController
@RequestMapping("/sysItemCategory")
public class SysItemCategoryController {

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
    //@RequiresRoles({"base","admin"}) //如果subject中有这些字典分类才可以访问方法。如果没有这个权限则会抛出异常AuthorizationException。
    //属于T1并且属于T2字典分类可以访问
    //@RequiresRoles(value={"T1","T2"})
    //属于T1或者T2字典分类可以访问
    //@RequiresRoles(value={"base","admin"},logical = Logical.OR)
    public SysItemCategory query(SysItemCategory param){

        logger.info("查询字典分类列表开始");
        SysItemCategory result = param;
        try {
            SysItemCategory SysItemCategory = new SysItemCategory();
            SysItemCategory.setParentId("-1");
            SysItemCategory.setOrderby("sort_key");
            List<SysItemCategory> rootMenus = dataBaseService.selectList("findSysItemCategory", SysItemCategory);

            param.setCustomWhere(" and parent_id <> '-1' ");
            if(StringUtils.isBlank(param.getOrderby())){
                param.setOrderby("sort_key asc");
            }
            List<SysItemCategory> rows = dataBaseService.selectList("findSysItemCategory", param);

            for (SysItemCategory m:rootMenus){
                m.setExpand(param.isExpand());
                this.recurrence(m,rows,param);
                //System.err.println(jsonSupport.toJson(m));
            }

            result.setRows(rootMenus);
            result.setSuccess(true);
            result.setMessage("查询数据成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询字典分类列表异常");
            logger.error("查询字典分类列表异常",e);
            e.printStackTrace();
        }
        logger.info("查询字典分类列表结束");
        return  result;
    }

    /*递归字典分类*/
    public void recurrence(SysItemCategory menu,List<SysItemCategory> menus,SysItemCategory params){

        for(SysItemCategory m:menus){

            if(StringUtils.equals(m.getParentId(),menu.getSid())){
                m.setExpand(params.isExpand());
                menu.getChildren().add(m);
                this.recurrence(m,menus,params);
            }

        }

    }

    @PostMapping(value = "/deletes")
    public SysItemCategory delete(@RequestBody SysItemCategory param){

        logger.info("删除字典分类开始");
        logger.info(jsonSupport.toJson(param));
        SysItemCategory result = new SysItemCategory();
        try {

            if(param.getChildren().size()>0){
                this.delChildrenMenu(param.getChildren());
            }

            SysItem sysItem = new SysItem();
            sysItem.setCategoryId(param.getSid());
            dataBaseService.delete("deleteSysItemByCategory",sysItem);

            dataBaseService.delete("deleteSysItemCategory", param);

            result.setSuccess(true);
            result.setMessage("删除字典分类成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("删除字典分类异常");
            logger.error("删除字典分类异常",e);
            e.printStackTrace();
        }

        logger.info("删除字典分类结束");
        return  result;
    }

    /*递归删除字典分类*/
    public void delChildrenMenu(List<SysItemCategory> rows){

        for(SysItemCategory m:rows){
            SysItem sysItem = new SysItem();
            sysItem.setCategoryId(m.getSid());
            dataBaseService.delete("deleteSysItemByCategory",sysItem);

            dataBaseService.delete("deleteSysItemCategory", m);
            this.delChildrenMenu(m.getChildren());
        }

    }


    @PostMapping(value = "/save")
    public SysItemCategory save(@RequestBody SysItemCategory param){

        logger.info("新增/修改字典分类开始 ");
        SysItemCategory result = param;
        try {
            String userCd = sessionSupport.getUserCd();
            Date systemDate = DateSupport.getSystemDate();

            param.setUpdateBy(userCd);
            param.setUpdateDate(systemDate);

            if(StringUtils.isBlank(param.getSid())){

                param.setCreatedBy(userCd);
                param.setCreatedDate(systemDate);
                dataBaseService.insert("addSysItemCategory",param);

                List<SysItem> sysItems = param.getSysItems();
                for (SysItem row:sysItems){
                    row.setCreatedBy(userCd);
                    row.setCreatedDate(systemDate);
                    row.setUpdateBy(userCd);
                    row.setUpdateDate(systemDate);
                    row.setCategoryId(param.getSid());
                    dataBaseService.insert("addSysItem",row);
                }

            }else{
                dataBaseService.update("updateSysItemCategory",param);

                List<SysItem> sysItems = param.getSysItems();
                for (SysItem row:sysItems){

                    row.setUpdateBy(userCd);
                    row.setUpdateDate(systemDate);
                    row.setCategoryId(param.getSid());

                    if(StringUtils.isBlank(row.getSid())){
                        row.setCreatedBy(userCd);
                        row.setCreatedDate(systemDate);
                        dataBaseService.insert("addSysItem",row);
                    }else {
                        dataBaseService.update("updateSysItem",row);
                    }

                }

            }

            result.setSuccess(true);
            result.setMessage("保存成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("保存异常");
            logger.error("保存异常",e);
            e.printStackTrace();
        }

        logger.info("新增/修改字典分类结束");
        return  result;
    }

    @RequestMapping(value = "/roleCodeIsExist")
    public SysItemCategory roleCodeIsExist(String userCd){

        logger.info("验证字典分类CD是否已存在开始");
        SysItemCategory result = new SysItemCategory();
        /*try {
            result.setRoleCode(userCd);
            SysItemCategory sysAccount = dataBaseService.selectOne("findSysItemCategory", result);
            result = sysAccount==null?result:sysAccount;
            result.setSuccess(true);
            result.setMessage("验证字典分类CD是否已存在成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("验证字典分类CD是否已存在异常");
            logger.error("验证字典分类CD是否已存在异常",e);
            e.printStackTrace();
        }*/

        logger.info("验证字典分类CD是否已存在结束");
        return  result;
    }

}
